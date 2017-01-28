package client

import (
	"encoding/json"
	"fmt"
	"net/http"
)

type UserClient struct {
	baseURL string
}

type User struct {
	Name string
}

func (c *UserClient) GetResource(id int) (*User, error) {
	url := fmt.Sprintf("%s/user/%d", c.baseURL, id)
	req, _ := http.NewRequest("GET", url, nil)

	client := &http.Client{}
	resp, err := client.Do(req)

	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	var res User
	decoder := json.NewDecoder(resp.Body)
	if err := decoder.Decode(&res); err != nil {
		return nil, err
	}

	return &res, nil
}
