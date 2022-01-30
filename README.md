# app-backend

## Local development
Inspired by [this tutorial](https://www.jetbrains.com/help/idea/run-and-debug-a-spring-boot-application-using-docker-compose.html)

### Prerequisites
- install Docker desktop
- install IntelliJ
- install the Docker plugin in IntelliJ

### Running the backend
- open the project in IntelliJ
- find the `docker-compose.yml` and open it
- click on the two green arrows IntelliJ offers you <br/><img width="240" alt="Screenshot 2022-01-30 at 11 16 55" src="https://user-images.githubusercontent.com/10157047/151695632-3e21e3ae-d594-4bcc-9d1e-a86e749f1b2b.png">
- set the tasks `backend:bootJar` and `docker build . -t app-backend` to be run before starting docker compose <img width="762" alt="Screenshot 2022-01-30 at 12 29 04" src="https://user-images.githubusercontent.com/10157047/151703498-e82fe722-f3ec-4dff-8c2e-f8989f74510c.png">
- you are good to go, both db and backend should be running now. Visit [localhost:5000](http://localhost:5000) in you browser to verify it
- you can stop the running services at any time in the `Services` tab of IntelliJ

You can also start the backend without IntelliJ. Run the following commands in its root directory:
- `./gradlew bootJar`
- `docker build . -t app-backend`
- `docker-compose up -d`
