package com.quangduong.SE114backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("weather")
public class WeatherAPI {

    List<Forecast> data = Arrays.asList(
            new Forecast("Vung Tau", 31, 13, 0.9f, 14.48f, "https://mfiles.alphacoders.com/701/701818.jpg"),
            new Forecast("Ho Chi Minh City", 30, 16, 0.92f, 9.66f, "https://cattour.vn/images/upload/images/Du-lich-vung-tau/16-cai-nhat-cua--vung-tau/vung-tau.jpg"),
            new Forecast("Ha Noi", 33, 11, 0.82f, 9.66f, "https://vietnam.travel/sites/default/files/inline-images/Wallpaper_Ha%20Noi_Vietnam%20Tourism.jpg"),
            new Forecast("Hai Phong", 37, 16, 0.85f, 9.66f, "https://media.vneconomy.vn/images/upload/2023/03/29/hai-phong-no-luc-doi-moi-53744.jpg"),
            new Forecast("Da Nang", 37, 3, 0.79f, 9.66f, "https://wall.vn/wp-content/uploads/2020/04/cau-rong-da-nang.jpg"),
            new Forecast("Can Tho", 24, 5, 1f, 6.44f, "https://c1.wallpaperflare.com/preview/657/800/1005/john-bridge-can-tho-hau-giang.jpg"),
            new Forecast("Da Lat", 19, 3, 0.9f, 6.44f, "https://i.pinimg.com/originals/7d/71/4d/7d714db4111413cf5f97e511385d4996.jpg"),
            new Forecast("Sa Pa", 21, 14, 0.79f, 10f, "https://scontent.iocvnpt.com/resources/portal/Images/LCI/adminsvhttdl/12%202020/Du%20l%E1%BB%8Bch%20Sa%20Pa%20v%C3%A0o%20m%C3%B9a%20%C4%91%C3%B4ng%20%C4%91%E1%BB%83%20th%C6%B0%E1%BB%9Fng%20th%E1%BB%A9c%20c%C3%A1i%20l%E1%BA%A1nh%20ng%E1%BB%8Dt%20ng%C3%A0o/thuong-thuc-mua-dong-sapa-2_547862786.jpg")
    );

    @GetMapping
    public ResponseEntity<?> getForecast(@RequestParam("q") String query) {
        Optional<Forecast> forecast = data.stream().filter(d -> d.city().toLowerCase().equals(query.trim().toLowerCase())).findFirst();
        if (forecast.isPresent())
            return ResponseEntity.ok(forecast);
        return ResponseEntity.ok().build();
    }

    record Forecast (String city, float temperature, float wind, float humidity, float visibility, String photo) {}

}
