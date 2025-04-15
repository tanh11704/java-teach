package lab3.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WeatherModel {
    private final String API_KEY = "7704477400e5cbd7ffc157dc2d127640";
    private final String cityNameConstant = "Danang,vn";
    
    private String cityName;
    private String temperature;
    private String feelLike;
    private String humidity;
    private String pressure;
    private String windSpeed;
    private String windDirection;
    private String weatherCondition;
    private String iconCode;
    private String lastUpdate;
    private String visibility;
    private String cloud;

    public WeatherModel() throws Exception {
        fetchWeatherData(cityNameConstant);
    }
    
    public void fetchWeatherData(String city) throws Exception {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city 
                    + "&appid=" + API_KEY + "&mode=xml&units=metric&lang=vi";
        
        URL apiUrl = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(apiUrl.openStream()));
        StringBuilder xmlBuilder = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            xmlBuilder.append(line).append("\n");
        }
        reader.close();
        
        parseWeatherData(xmlBuilder.toString());
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getIconCode() {
        return iconCode;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getFeelLike() {
        return feelLike;
    }

    public String getCloud() {
        return cloud;
    }
    
    private void parseWeatherData(String xmlData) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));
        
        Element root = doc.getDocumentElement();
        
        // Thông tin thành phố
        Element cityElement = (Element) root.getElementsByTagName("city").item(0);
        this.cityName = cityElement.getAttribute("name");
        
        // Nhiệt độ
        Element tempElement = (Element) root.getElementsByTagName("temperature").item(0);
        this.temperature = tempElement.getAttribute("value");
        
        // Cảm giác như
        Element feelLikeElement = (Element) root.getElementsByTagName("feels_like").item(0);
        this.feelLike = feelLikeElement.getAttribute("value");
        
        // Độ ẩm
        Element humidityElement = (Element) root.getElementsByTagName("humidity").item(0);
        this.humidity = humidityElement.getAttribute("value") + humidityElement.getAttribute("unit");
        
        // Áp suất
        Element pressureElement = (Element) root.getElementsByTagName("pressure").item(0);
        this.pressure = pressureElement.getAttribute("value") + " " + pressureElement.getAttribute("unit");
        
        // Gió
        Element windElement = (Element) root.getElementsByTagName("wind").item(0);
        Element speedElement = (Element) windElement.getElementsByTagName("speed").item(0);
        this.windSpeed = speedElement.getAttribute("value");
        
        Element directionElement = (Element) windElement.getElementsByTagName("direction").item(0);
        this.windDirection = directionElement.getAttribute("name");
        
        // Tình trạng thời tiết
        Element weatherElement = (Element) root.getElementsByTagName("weather").item(0);
        this.weatherCondition = weatherElement.getAttribute("value");
        this.iconCode = weatherElement.getAttribute("icon");
        
        // Thời gian cập nhật
        Element lastUpdateElement = (Element) root.getElementsByTagName("lastupdate").item(0);
        String updateValue = lastUpdateElement.getAttribute("value");
        
        // Tầm nhìn
        Element visibility = (Element) root.getElementsByTagName("visibility").item(0);
        this.visibility = visibility.getAttribute("value");
        
        // Mây
        Element clouds = (Element) root.getElementsByTagName("clouds").item(0);
        this.cloud = clouds.getAttribute("value");
        
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            Date updateDate = inputFormat.parse(updateValue);
            this.lastUpdate = outputFormat.format(updateDate);
        } catch (Exception e) {
            this.lastUpdate = updateValue;
        }
    }
}
