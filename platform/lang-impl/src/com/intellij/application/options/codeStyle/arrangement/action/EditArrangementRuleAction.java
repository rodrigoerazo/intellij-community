/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.application.options.codeStyle.arrangement.action;

import com.intellij.application.options.codeStyle.arrangement.match.ArrangementMatchingRulesControl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Toggleable;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.openapi.project.DumbAware;
import gnu.trove.TIntArrayList;

/**
 * @author Denis Zhdanov
 * @since 10/29/12 11:01 AM
 */
public class EditArrangementRuleAction extends AnAction implements DumbAware, Toggleable {

  public EditArrangementRuleAction() {
    getTemplatePresentation().setText(ApplicationBundle.message("arrangement.action.rule.edit.text"));
    getTemplatePresentation().setDescription(ApplicationBundle.message("arrangement.action.rule.edit.description"));
  }

  @Override
  public void update(AnActionEvent e) {
    ArrangementMatchingRulesControl control = ArrangementMatchingRulesControl.KEY.getData(e.getDataContext());
    e.getPresentation().setEnabled(control != null && control.getSelectedModelRows().size() == 1);
  }

  @Override
  public void actionPerformed(AnActionEvent e) {
    ArrangementMatchingRulesControl control = ArrangementMatchingRulesControl.KEY.getData(e.getDataContext());
    if (control == null) {
      return;
    }
    TIntArrayList rows = control.getSelectedModelRows();
    if (rows.size() != 1) {
      return;
    }
    control.showEditor(rows.get(0));
  }
}
