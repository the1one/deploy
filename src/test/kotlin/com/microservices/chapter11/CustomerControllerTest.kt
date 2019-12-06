package com.microservices.chapter11

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `we should get customer list`(){

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$[0].id").value(1))
                .andExpect(jsonPath("\$[0].name").value("Kotlin"))
                .andExpect(jsonPath("\$[1].id").value(2))
                .andExpect(jsonPath("\$[1].name").value("Spring"))
                .andExpect(jsonPath("\$[2].id").value(3))
                .andExpect(jsonPath("\$[2].name").value("Microservice"))
                .andDo(print())

    }


    @Test
    fun `we should get a customer by id`() {
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.id").value(1))
                .andExpect(jsonPath("\$.name").value("Kotlin"))
                .andDo(print())
    }
}