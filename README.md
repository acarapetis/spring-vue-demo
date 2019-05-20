# spring-vue-demo

A simple demonstration web application built quickly with Spring Boot, Vue.js
and Bootstrap. Works in (current versions of) Firefox, Chrome and Edge.
Definitely does not work in Internet Explorer. (See
`src/main/resources/static/index.html` for some comments on compatibility.)

## Running

If you have OpenJDK 8 and maven installed, all you should need to do is clone
this repository and run `mvn spring-boot:run`, and (after dependency installation
and compilation) the web application will be available at http://localhost:8080/.

(A note to those that requested this: I tried briefly to get this to run using
the basic jetty maven plugin, but had no luck configuring it quickly and didn't
want to spend too much more time. I hope the spring-boot-starter-jetty plugin
is acceptable.)
