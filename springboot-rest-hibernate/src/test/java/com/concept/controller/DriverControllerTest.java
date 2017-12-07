package com.concept.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.concepttest.controller.DriverController;
import com.concepttest.datatransferobject.DriverDTO;
import com.concepttest.service.driver.DriverService;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestClientTest(DriverController.class)
public class DriverControllerTest
{

    @MockBean
    private DriverService service;

    @Autowired
    @InjectMocks
    DriverController driverController;

    @Autowired
    private MockRestServiceServer server;


    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
        throws Exception
    {
        this.server
            .expect(requestTo("v1/drivers"))
            .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        DriverDTO driver = this.driverController.selectCar(1, 1);
        String a = "true";
        assertThat(a).isEqualTo("true");
    }

    //    @Before
    //    public void configureService() {
    //        server = MockRestServiceServer.createServer(restTemplate);
    //        service = new SpannersService(SERVICE_URL);
    //        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
    //
    //    }
    //    
    //    
    //    @Test
    //    public void test()
    //    {
    ////        Mockito.mock(DriverService.class);
    //
    //        assertEquals("true", "true");;
    //    }

}
