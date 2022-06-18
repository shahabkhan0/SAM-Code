package com.vc.technologies.sam.interfaces;


import com.vc.technologies.sam.models.UserModel;

import java.util.List;

public interface UserData {

    void onUserDataLoaded(List<UserModel> dataModelList);

    void onUserDataLoadedFailed(String message);
}
