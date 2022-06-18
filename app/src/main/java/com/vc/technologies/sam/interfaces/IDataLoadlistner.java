package com.vc.technologies.sam.interfaces;

import com.vc.technologies.sam.models.PostModel;

import java.util.List;

public interface IDataLoadlistner {

    void onDataLoaded(List<PostModel> dataModelList);

    void onDataLoadedFailed(String message);
}
