package j2html.tags;

import org.junit.Test;

import j2html.TagCreator;
import j2html.attributes.Attr;

import static j2html.TagCreator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComplexRenderTest {

    private String renderTest() {
        boolean USER_SHOULD_LOG_IN = true;
        boolean USER_SHOULD_SIGN_UP = false;
        return document().render() +
            html().with(
                head().with(
                    title().withText("Test")
                ),
                body().with(
                    header().with(
                        h1().with(
                            text("Test Header "),
                            a("with link").withHref("http://example.com"),
                            text(".")
                        )
                    ),
                    main().with(
                        h2("Test Form"),
                        div().with(
                            input().withType("email").withName("email").withPlaceholder("Email"),
                            input().withType("password").withName("password").withPlaceholder("Password")
                        ).condWith(USER_SHOULD_LOG_IN, button().withType("submit").withText("Login")
                        ).condWith(USER_SHOULD_SIGN_UP, button().withType("submit").withText("Signup"))
                    ),
                    footer().attr(Attr.CLASS, "footer").condAttr(1 == 1, Attr.ID, "id").withText("Test Footer"),
                    script().withSrc("/testScript.js")
                )
            ).render();
    }

    private String renderTest2() {
        boolean USER_SHOULD_LOG_IN = true;
        boolean USER_SHOULD_SIGN_UP = false;
        return document().render() +
            html(
                head(
                    title("Test")
                ),
                body(
                    header(
                        h1(
                            text("Test Header "),
                            a("with link").withHref("http://example.com"),
                            text(".")
                        )
                    ),
                    main(
                        h2("Test Form"),
                        div(
                            input().withType("email").withName("email").withPlaceholder("Email"),
                            input().withType("password").withName("password").withPlaceholder("Password"),
                            iff(USER_SHOULD_LOG_IN, button().withType("submit").withText("Login")),
                            iff(USER_SHOULD_SIGN_UP, button().withType("submit").withText("Signup"))
                        )
                    ),
                    footer("Test Footer").attr(Attr.CLASS, "footer").condAttr(1 == 1, Attr.ID, "id"),
                    script().withSrc("/testScript.js")
                )
            ).render();
    }

    private String renderTest3() {
        boolean USER_SHOULD_LOG_IN = true;
        boolean USER_SHOULD_SIGN_UP = false;
        return document().render() + "\n" +
            html(
                head(
                    title("Test")
                ),
                body(
                    header(
                        h1(
                            text("Test Header "),
                            a("with link").withHref("http://example.com"),
                            text(".")
                        )
                    ),
                    main(
                        h2("Test Form"),
                        div(
                            input().withType("email").withName("email").withPlaceholder("Email"),
                            input().withType("password").withName("password").withPlaceholder("Password"),
                            iff(USER_SHOULD_LOG_IN, button().withType("submit").withText("Login")),
                            iff(USER_SHOULD_SIGN_UP, button().withType("submit").withText("Signup"))
                        )
                    ),
                    footer("Test Footer").attr(Attr.CLASS, "footer").condAttr(1 == 1, Attr.ID, "id"),
                    script().withSrc("/testScript.js")
                )
            ).renderFormatted();
    }

    @Test
    public void testComplexRender() {
        String expectedResult = "<!DOCTYPE html><html><head><title>Test</title></head><body><header><h1>Test Header <a href=\"http://example.com\">with link</a>.</h1></header><main><h2>Test Form</h2><div><input type=\"email\" name=\"email\" placeholder=\"Email\"><input type=\"password\" name=\"password\" placeholder=\"Password\"><button type=\"submit\">Login</button></div></main><footer class=\"footer\" id=\"id\">Test Footer</footer><script src=\"/testScript.js\"></script></body></html>";
        assertThat(renderTest(), is(expectedResult));
        assertThat(renderTest2(), is(expectedResult));
    }

    @Test
    public void testComplexRender_formatted() {
        assertThat(renderTest3(),
                   is("<!DOCTYPE html>\n"
                          + "<html>\n"
                          + "    <head>\n"
                          + "        <title>\n"
                          + "            Test\n"
                          + "        </title>\n"
                          + "    </head>\n"
                          + "    <body>\n"
                          + "        <header>\n"
                          + "            <h1>\n"
                          + "                Test Header \n"
                          + "                <a href=\"http://example.com\">\n"
                          + "                    with link\n"
                          + "                </a>\n"
                          + "                .\n"
                          + "            </h1>\n"
                          + "        </header>\n"
                          + "        <main>\n"
                          + "            <h2>\n"
                          + "                Test Form\n"
                          + "            </h2>\n"
                          + "            <div>\n"
                          + "                <input type=\"email\" name=\"email\" placeholder=\"Email\">\n"
                          + "                <input type=\"password\" name=\"password\" placeholder=\"Password\">\n"
                          + "                <button type=\"submit\">\n"
                          + "                    Login\n"
                          + "                </button>\n"
                          + "            </div>\n"
                          + "        </main>\n"
                          + "        <footer class=\"footer\" id=\"id\">\n"
                          + "            Test Footer\n"
                          + "        </footer>\n"
                          + "        <script src=\"/testScript.js\">\n"
                          + "        </script>\n"
                          + "    </body>\n"
                          + "</html>\n"));
    }
}
