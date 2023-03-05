package com.example.mvc.controllers;

import com.example.mvc.models.Car;
import com.example.mvc.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/showlist")
    public String showAllCars(ModelMap mm) {

        mm.addAttribute("cars", carService.findAll());
        return "carlist";
    }

    @PostMapping("/insert")
    public String insertCar(@RequestParam("plate") String plate,
                            @RequestParam("power") Integer power,
                            @RequestParam("insurance") MultipartFile insurance,
                            ModelMap mm) {

        Car car = new Car(plate, power);
        car.setInsurancefilename(insurance.getOriginalFilename());
        try {
            car.setInsurancefile(insurance.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        carService.insert(car);
        return "redirect:showlist";
    }

    @GetMapping("/update/{carid}")
    public String updateCar(@PathVariable Long carid, ModelMap mm) {

        Car car = carService.getById(carid);
        System.out.println(car);
        mm.addAttribute("cartoupdate", car);
        return "carupdate";
    }

    @PostMapping("/doupdate")
    public String doupdate(@RequestParam("carid") Long id,
                           @RequestParam("plate") String plate,
                           @RequestParam("power") Integer power, ModelMap mm) {

        Car car = carService.getById(id);
        car.setPlate(plate);
        car.setPower(power);
        carService.insert(car);
        mm.addAttribute("cars", carService.findAll());
        return "carlist";
    }

    @GetMapping("/delete/{carid}")
    public String deleteCar(@PathVariable Long carid, ModelMap mm) {

        Car car = carService.getById(carid);
        carService.delete(car);
        mm.addAttribute("cars", carService.findAll());
        return "carlist";
    }


//        @GetMapping(value = "/download/{carid}",
//                produces = MediaType.APPLICATION_PDF_VALUE)
//        @ResponseBody
//        public byte[] downloadInsurance(@PathVariable Integer carid){
//        Car car = cs.getById(carid);
//        return car.getInsurancefile();
//    }


    @GetMapping(value = "/download/{carid}")
    public ResponseEntity<Resource> downloadInsurance(@PathVariable Long carid) {

        Car car = carService.getById(carid);
        byte[] insurance = car.getInsurancefile();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + car.getInsurancefilename());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(insurance.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(insurance));
    }

}
