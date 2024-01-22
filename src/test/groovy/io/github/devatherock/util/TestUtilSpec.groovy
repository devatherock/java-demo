package io.github.devatherock.util

import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.core5.http.ClassicHttpResponse
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.util.Timeout
import spock.lang.Specification

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test http client connection timeout'() {
        given:
        var requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(25))
                .build();
        var connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(50);
        connManager.setDefaultMaxPerRoute(50);
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connManager)
                .build();

        and:
        List<Thread> threads = []
        List<Exception> exceptions = new CopyOnWriteArrayList<>()

        when:
        for (int index = 0; index < 45; index++) {
            threads.add(Thread.start {
                try {
                   client.execute(new HttpGet("https://www.example.com"), ClassicHttpResponse::close);
                } catch (Exception exception) {
                    exceptions.add(exception)
                }
            })
        }

        for (int index = 0; index < 45; index++) {
            threads[index].join()
        }
        exceptions.each {it.printStackTrace()}

        then:
        exceptions.size() == 0

    }
}