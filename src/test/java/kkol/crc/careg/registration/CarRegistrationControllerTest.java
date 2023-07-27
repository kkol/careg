package kkol.crc.careg.registration;

import jakarta.servlet.ServletException;
import kkol.crc.careg.controller.CarRegistrationController;
import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.model.FuelType;
import kkol.crc.careg.model.User;
import kkol.crc.careg.repository.CarRepository;
import kkol.crc.careg.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(CarRegistrationController.class)
public class CarRegistrationControllerTest {

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> carObjectMapping;

    @Autowired
    private JacksonTester<CarDTO> carDTOObjectMapping;

    @Test
    public void postValidCar() throws Exception {
        //given
        CarDTO carDTO = CarDTO.builder()
                .model("test_model_new")
                .mark("test_mark_new")
                .productionYear(1990)
                .enginePower(32f)
                .fuelType(FuelType.BIODIESEL.name())
                .weight(123f)
                .build();

        Car expectedCar = new Car(null, new User(), "test_mark_new", "test_model_new", FuelType.BIODIESEL.name(), 1990, 34f, 123f, false);
        given(carService.registerCar(carDTO)).willReturn(expectedCar);

        //when
        MockHttpServletResponse response = mvc.perform(post("/car/register")
                        .contentType(MediaType.APPLICATION_JSON).content(carDTOObjectMapping.write(carDTO).getJson()))
                .andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void getCarByIdTest() throws Exception {
        //given
        Car expectedCar = new Car(52L, new User(), "test_mark_new", "test_model_new", FuelType.BIODIESEL.name(), 1990, 34f, 123f, false);

        //when
        when(carRepository.findById(52L)).thenReturn(Optional.of(expectedCar));
        MockHttpServletResponse response = mvc.perform(get("/car?id=52")).andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(carObjectMapping.write(expectedCar).getJson());
    }

    @Test
    public void getNonExistingCarById() throws Exception {
        //given
        Car expectedCar = new Car(52L, new User(), "test_mark_new", "test_model_new", FuelType.BIODIESEL.name(), 1990, 34f, 123f, false);

        //when
        when(carRepository.findById(52L)).thenReturn(Optional.of(expectedCar));
        Assertions.assertThrows(ServletException.class, () -> mvc.perform(get("/car?id=60")));
    }

    @Test
    public void deleteExistingCar() throws Exception {
        //given
        Car expectedCar = new Car(52L, new User(), "test_mark_new", "test_model_new", FuelType.BIODIESEL.name(), 1990, 34f, 123f, false);

        given(carRepository.findById(any())).willReturn(Optional.of(expectedCar));

        //when
        MockHttpServletResponse response = mvc.perform(delete("/car/delete/52")).andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(200);

    }
/*
    @Test
    public void deleteNonExistingUser() throws Exception {
        //given
        given(carRepository.findById(any())).willReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows()
        mvc.perform(delete("/car/delete/52")).andExpect(status().isBadRequest());

    }*/

}
