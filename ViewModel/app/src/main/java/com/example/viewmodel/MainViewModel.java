package com.example.viewmodel;


pu
        blic class MainViewModel extends ViewModel {
    private static final String API_KEY = "c62ba897fef010bea8ebf378ac2f71d9";
    //alternatif API_KEY
    //private static final String API_KEY = "2adbb675bdd05bfd3b547a01f9938462";

    private MutableLiveData<ArrayList<WeatherItems>> listWeathers = new MutableLiveData<>();

    public static String getApiKey() {
        return API_KEY;
    }

    public MutableLiveData<ArrayList<WeatherItems>> getListWeathers() {
        return listWeathers;
    }

    public void setListWeathers(MutableLiveData<ArrayList<WeatherItems>> listWeather) {
        this.listWeathers = listWeather;
    }

    void setWeather (final String cities){
        //Request via Web API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<WeatherItems> listItems = new ArrayList<>();
        String url = "https://api.openweathermap.org/data/2.5/group?id="
                + cities + "&units=metric&appid=" + API_KEY;


        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);
                        listItems.add(weatherItems);
                    }
                    listWeathers.postValue(listItems);

                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
}
