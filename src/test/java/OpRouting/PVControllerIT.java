package OpRouting;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PVControllerIT {

    @LocalServerPort
    private int port;

    private URL base;
    private URL base2;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/PartVerifier/raw?group=705-2422&key=OPERATIONS");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("{\"group\":\"705-2422\",\"key\":\"OPERATIONS\",\"value\":\"NODAL, 200%\"}"));
    }

    @Before
    public void setUp2() throws Exception {
        this.base2 = new URL("http://localhost:" + port + "/PartVerifier/raw?group=605-4776&key=ARRAYSIZE&value=10");
    }

    @Test
    public void putHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base2.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("{\"group\":\"605-4776\",\"key\":\"ARRAYSIZE\",\"value\":\"10\"}"));
    }
}