package pact_kotlin_provider

import au.com.dius.pact.provider.junit.PactRunner
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactFolder
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.target.HttpTarget
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.junit.target.Target
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.restdriver.clientdriver.ClientDriverRule
import com.github.restdriver.clientdriver.RestClientDriver.*
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.Before
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 *
 * @author nsoushi
 */

@RunWith(PactRunner::class)
@Provider("provider_user_client")
@PactFolder("pacts")
@WebAppConfiguration
open class ContractUserTest {

    lateinit var wireMockServer: WireMockServer

    companion object {

        // mock port
        private val port = 8080

        @TestTarget
        lateinit var target: Target

        @BeforeClass @JvmStatic fun setUpService() {
            target = HttpTarget(port)
        }
    }

    @Before
    fun before() {
        wireMockServer = WireMockServer(port)
        wireMockServer.start()
        WireMock.configureFor(port)
    }

    @State("fetch user by id 1192")
    open fun toDefaultState() {

        val path = "/user/1192"

        val target = UserController()
        val mvc = MockMvcBuilders.standaloneSetup(target).build()
        val mvcResult = mvc.perform(MockMvcRequestBuilders.get(path)).andReturn()

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(mvcResult.response.status)
                        .withHeader("Content-Type", mvcResult.response.getHeader("Content-Type"))
                        .withBody(mvcResult.response.contentAsString)))
    }
}