package core.us.base.filters

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.us.base.library.entities.beans.CoreSessionData
import io.micronaut.core.annotation.Order
import io.micronaut.core.order.Ordered
import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.cookie.Cookie
import io.micronaut.http.cookie.Cookies
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher

@Order(10000)
@Filter("/api/**")
class SessionDataFilter implements HttpServerFilter, Ordered {

    @Override
    int getOrder() {
        return 10000
    }

    Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        Cookies cookies
        Cookie cookie
        String cookieString, payload
        String[] chunks
        CoreSessionData sessionData
        Map sessionDataMap
        Base64.Decoder decoder = Base64.getUrlDecoder()
        ObjectMapper mapper = new ObjectMapper()

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        if (request.getCookies().contains("JWT")){
            cookies = request.getCookies()
            cookie = cookies.get("JWT")
            cookieString = cookie.value
            chunks = cookieString.split("\\.")
            payload = new String(decoder.decode(chunks[1]))

            sessionDataMap = mapper.readValue(payload, Map)
            sessionData = new CoreSessionData(sessionDataMap.sub as String, sessionDataMap.companyCode as String, sessionDataMap.languageCode as String, sessionDataMap.countryCode as String)
            request.setAttribute("sessionData", mapper.convertValue(sessionData, Map))

        } else {
            sessionData = new CoreSessionData()
            request.setAttribute("sessionData", mapper.convertValue(sessionData, Map))
        }
        return chain.proceed(request)
    }

}
