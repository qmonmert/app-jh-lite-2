package tech.jhipster.sport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static tech.jhipster.sport.SportApp.LF;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@IntegrationTest
class SportAppIT {

  @Test
  void shouldMain() {
    assertThatCode(() -> SportApp.main(new String[] {})).doesNotThrowAnyException();
  }

  @Test
  void shouldApplicationRunning() {
    String result = SportApp.applicationRunning("jhlite");
    assertThat(result).isEqualTo("  Application 'jhlite' is running!" + LF);
  }

  @Test
  void shouldAccessUrlLocalWithoutServerPort() {
    String result = SportApp.accessUrlLocal(null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlLocalWithoutContextPath() {
    String result = SportApp.accessUrlLocal("http", "8080", "/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/" + LF);
  }

  @Test
  void shouldAccessUrlLocalWithContextPath() {
    String result = SportApp.accessUrlLocal("http", "8080", "/lite/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/lite/" + LF);
  }

  @Test
  void shouldAccessUrlExternalWithoutServerPort() {
    String result = SportApp.accessUrlExternal(null, null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlExternalWithoutContextPath() {
    String result = SportApp.accessUrlExternal("http", "127.0.1.1", "8080", "/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/" + LF);
  }

  @Test
  void shouldAccessUrlExternalWithContextPath() {
    String result = SportApp.accessUrlExternal("http", "127.0.1.1", "8080", "/lite/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/lite/" + LF);
  }

  @Test
  void shouldConfigServerWithoutConfigServerStatus() {
    String result = SportApp.configServer(null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldConfigServer() {
    String result = SportApp.configServer("Connected to the JHipster Registry running in Docker");
    assertThat(result).contains("Config Server: Connected to the JHipster Registry running in Docker");
  }

  @Test
  void shouldGetProtocol() {
    assertThat(SportApp.getProtocol(null)).isEqualTo("http");
  }

  @Test
  void shouldGetProtocolForBlank() {
    assertThat(SportApp.getProtocol(" ")).isEqualTo("https");
  }

  @Test
  void shouldGetProtocolForValue() {
    assertThat(SportApp.getProtocol("https")).isEqualTo("https");
  }

  @Test
  void shouldGetContextPath() {
    assertThat(SportApp.getContextPath("/chips")).isEqualTo("/chips");
  }

  @Test
  void shouldGetContextPathForNull() {
    assertThat(SportApp.getContextPath(null)).isEqualTo("/");
  }

  @Test
  void shouldGetContextPathForBlank() {
    assertThat(SportApp.getContextPath(" ")).isEqualTo("/");
  }

  @Test
  void shouldGetHost() {
    assertThatCode(SportApp::getHostAddress).doesNotThrowAnyException();
  }

  @Test
  void shouldGetHostWithoutHostAddress() {
    try (MockedStatic<InetAddress> inetAddress = Mockito.mockStatic(InetAddress.class)) {
      inetAddress.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());

      String result = SportApp.getHostAddress();

      assertThat(result).isEqualTo("localhost");
    }
  }
}
