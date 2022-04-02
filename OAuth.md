# OAuth 

## Opening the OAuth login

Open `GET /login-redirect?redirectUri=<your redirect uri>`.

`redirectUri` is a required parameter. It must be one of the whitelisted OAuth redirect urls.

The call will respond with a redirect to the OAuth login page where the user can enter their credentials.
Once the user logged in you the page will redirect again to your redirect uri where you will get the login code as query parameter `code`.
Example: `https://dev.backend.app.links-wien.at/login-redirect?redirectUri=http://localhost:8080/oauth` -> will redirect to `http://localhost:8080/oauth?code=<your code>`

## Obtaining the Token

Make a call to `POST /api/v1/login`. It has two required parameters (send them as multipart/form body): `code` - the code obtained in the previous step and `redirectUri` - the same uri as in the first step.

Success Response:
```json
{
	"access_token": "eyJhbGciOiJSUzI1Niiwia20ajMtVjlKOV...bi11VlkqdGJodHRwczovmFtZS9Lw",
	"expires_in": 86400,
	"refresh_expires_in": 259200,
	"refresh_token": "eyJhbGciOiJIUzI1NiIsInOiAiSldUIi...C04NDA4LTU0Zi1hYzZgl8C3VSq3Fc",
	"token_type": "bearer",
	"scope": "email profile"
}
```

## Using the token

Send the received `accessToken` as bearer token (`Authorization: Bearer <token>` header) to every endpoint that needs authentication.




