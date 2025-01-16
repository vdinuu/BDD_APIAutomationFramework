package data_generation;

public enum APIResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private String endpoint;


    APIResources(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getEndpoint(){
        return endpoint;
    }
}
