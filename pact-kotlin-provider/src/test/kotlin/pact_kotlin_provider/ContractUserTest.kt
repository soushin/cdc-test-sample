package pact_kotlin_provider

import au.com.dius.pact.provider.junit.Consumer
import au.com.dius.pact.provider.junit.PactRunner
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit.target.HttpTarget
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.junit.target.Target
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
@Provider("user_service")
@Consumer("gateway_service")
//@PactFolder("pacts")
@PactBroker(host = "localhost", port = "8080", tags = arrayOf("latest"))
@WebAppConfiguration
open class ContractUserTest {

    lateinit var wireMockServer: WireMockServer

    companion object {

        // mock port
        private val port = 8081

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

    @State("there is a user named 1192-User")
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