package com.bielik.quwitestapp.dialog;

import com.bielik.quwitestapp.base.BaseView;

public interface EditProjectNameDialogView extends BaseView {

    void onProjectNameEdited(String newName);
    void onProjectNameEmpty();
    void onProjectNameNotChanged();
}
