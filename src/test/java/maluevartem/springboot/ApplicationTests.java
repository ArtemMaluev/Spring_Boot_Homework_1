package maluevartem.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private final static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8888);
    @Container
    private final static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8889);

    @Test
    void containerInfo() {
        System.out.println("devApp: " +
                "\n" + devApp.getContainerInfo() +
                "\n" + devApp.getDockerImageName() +
                "\n" + devApp.getContainerId() +
                "\n" + devApp.getHost() +
                "\n" + devApp.getExposedPorts() +
                "\n" + devApp.getMappedPort(8888));

        System.out.println();
        System.out.println("prodApp: " +
                "\n" + prodApp.getContainerInfo() +
                "\n" + prodApp.getDockerImageName() +
                "\n" + prodApp.getContainerId() +
                "\n" + prodApp.getHost() +
                "\n" + prodApp.getExposedPorts() +
                "\n" + prodApp.getMappedPort(8889));
    }

    @Test
    void devAppTest() {
        ResponseEntity<String> forEntityFirst = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8888) + "/profile", String.class);
        System.out.println(forEntityFirst.getBody());
        Assertions.assertEquals("Current profile is dev", forEntityFirst.getBody());
    }

    @Test
    void prodAppTest() {
        ResponseEntity<String> forEntitySecond = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8889) + "/profile", String.class);
        System.out.println(forEntitySecond.getBody());
        Assertions.assertEquals("Current profile is production", forEntitySecond.getBody());
    }
}
