package client

import (
	pact "github.com/SEEK-Jobs/pact-go"
	"github.com/SEEK-Jobs/pact-go/provider"
	"net/http"
	"testing"
)

func buildPact() pact.Builder {
	return pact.
	NewConsumerPactBuilder(&pact.BuilderConfig{PactPath: "../../pacts"}).
		ServiceConsumer("consumer_user_client").
		HasPactWith("provider_user_client")
}

func Test_ContractUserClientProvider_StatusIsOk(t *testing.T) {

	builder := buildPact()
	ms, msUrl := builder.GetMockProviderService()

	request := provider.NewJSONRequest("GET", "/user/1192", "", nil)
	header := make(http.Header)
	header.Add("content-type", "application/json")
	response := provider.NewJSONResponse(200, header)
	response.SetBody(`{"Name": "1192-User"}`)

	if err := ms.Given("fetch user by id 1192").
		UponReceiving("get request for user with id 1192").
		With(*request).
		WillRespondWith(*response); err != nil {
		t.Error(err)
		t.FailNow()
	}

	// Test request user client
	client := &UserClient{baseURL: msUrl}
	if _, err := client.GetResource(1192); err != nil {
		t.Error(err)
		t.FailNow()
	}

	// Verify registered interaction
	if err := ms.VerifyInteractions(); err != nil {
		t.Error(err)
		t.FailNow()
	}

	// Clear interaction for this test scope, if you need to register and verify another interaction for another test scope
	ms.ClearInteractions()

	//Finally, build to produce the pact json file
	if err := builder.Build(); err != nil {
		t.Error(err)
	}
}