package com.spark.controller;

import com.spark.common.controller.BaseController;
import com.spark.common.model.ResponseInfo;
import com.spark.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2017/7/30.
 */
@RestController
@RequestMapping(value = "/rest/photo")
public class PhotoResource extends BaseController{

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    public ResponseInfo<Boolean> deletePhotoById(
            @RequestParam(value = "photoId") Long photoId
    ) {
        ResponseInfo responseInfo;
        try {
            photoService.delete(photoId);
            responseInfo = buildSuccessRetunInfo();
            responseInfo.setData(true);
        } catch (Error e) {
            responseInfo = buildResourceNoFoundErrorRetunInfo();
            responseInfo.setData(false);
        }
        return responseInfo;
    }
}
