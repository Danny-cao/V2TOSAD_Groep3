package define.model;


public class ServiceProvider {

    private static DefineService defineService = new DefineService();

    public static DefineService getDefineService() {

        return defineService;

    }

}

