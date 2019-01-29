package service;

import transform.model.TransformService;

public class ServiceProvider {

    private static TransformService transformService = new TransformService();

    public static TransformService getTransformService(){

        return transformService;

    }

}
