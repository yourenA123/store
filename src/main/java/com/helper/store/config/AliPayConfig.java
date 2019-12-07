package com.helper.store.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@ToString
@Repository
public class AliPayConfig {

    public static final String APP_ID = "2016101400680959";
    //APPID
    public static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6UZxJStNyBjSrgEpRtjFiANPhvo9QzPo2tBsZYZCEjrywiFpV9dpq22YbyzLYrCaGtBOQM3FF2jkq0WBQSJqcyvqRB6tZiF7GrQkfGokymMTpmlj/efCrqI+eV+qw5+ivW2MhX+7y3JMooi4CBDumZvzVAQp+UqermP60KtM0kamOgpWNxo1FlBi4WLs/r299Y6l/qKRwIbR2K0ZHnOXO3dyWR+mBsagRZ6WgWaoXrDDafmTN3miO5Fgm4zOSAG75BziM+uIyF6v6oHA+Cedhx0BOxvWMo825lpzyaWAOXFRxGqjWGSQG5NkSZ1z8Mtt2/axHydFDMa+ZzbHT2LTRAgMBAAECggEBAKQky+RCGKBG2VYSMudj9IObqQ8jO816mCFjcNBehNxTh+aaacN7Ug579awcy1HZ8CMSOH385BkfFOglB44TOKtTo4Xr1MiO/LhZr0SIs0AxGKGZCjkgq6f/W2+rkqaB2RvMDjxT8QZizv6oSpIaOpbx+mKJhT4fAXqK0jHV3LZc7H1WJaenbKx5vBm6gSfEavJlfd/wVrHFrGc/BDaahUxojZYqRtFGTfXwH6a9VhHuJpKtHdseHEkg0JU7lN9UJZ47GhbjMHbBeibdEpqomKdMzkjyFcYoIlI89MjsLMKUWwaqhG+1UpdZC4kJJ6I5KutqNZVUVhV2ZyhRCEHUVVUCgYEA/Z2NL2mkJoYllI48L6tVCy6kpih02xdTteSZFVY5DArGg4w+CL/jCuOISQjezPgp5D3O7ICtkzUNc7Z0ZI0jDmnYj0idNo7nG7RmXtNrRaFp2z7ZKogmF5FwV9b5Hd77nTbeHhDrtXN/+IcikP9I++JeqJtvOsZNCIVPqfN6wesCgYEAvBITtYtWYypm8e62Wm7W+zCG9WBaWGbWGUrHDxeAJfHyfka+YvA1ESj33qJWrxo7Tk57UOjkRYA7a0GPtlBP18tFjnJKfga0UlucS2d5ok5eBHG5ygFrNO8DVu+5kezmXA/fY8y4eSXLS9gQszJDW5AlkwXzM2eIbeNwcns0eTMCgYA0BJaqvN5AUI9wsuPBW0DsNDrtIasmLNisnCCR8cdtouAopyEqJkFpQFX5tOoN3HCrMco5VANGCEGkFC43WYNxN2CFsv0b7Z+VXSEKHg8IjxzpLTDZumclulzyh1M9PzkYe+J/BDV0WCZNUVpTpyjPERUdPBRKV9YBA2bdkuUA1QKBgHkjV6iXAGTNehOZQ8HKJRUUs8k3b9oDGyKdZMxWwccvKZemr5kAyd2susOfYyBUUCDnMkzyRZ5ys1Mpnoy6/EKt/rZ67XpxjRKDSY/ULaeavLo2GoqAEXK+BTOpI6lXoyVnX+djhOsbqe/HUzJpTLOcjGc/7N+Ob3UAi3xh2XG7AoGBAOI9iyjM3mTvlDK6v3Pl84m+yj5owYi5FqmAV9KLBs/UVx8633t+EzACq6olPAv844Pr2cubH3sufPBnNrJy8UM2T5xD7UOkTkkkxKBtBRiK4URIttmiv/oRurCbS65HMJnY54YCDiOHwxp7CKLvqzfRUrqQLxw8HjBZNP0EqIOz";
    //你的应用私钥
    public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAji4xLRR2Zf6asAE3VRhasIBnSpQ8nVZR9CTN6xc6HK4V1uOW0OAO1YUjZVU3uRtXaoc6h52x4a4eIyvyrKqc2WV2bNfQBXL4jyLMmQNLtfv4Cu8lp1nkAJn63cSuk/+QcbYVScKmibonLBQPcEt+ydWad9mf4mJ6+Bspkvgdn59m4yY6ahd4peEtguM43agxdugIF7kceMIc5SCugxL19a74HD+8mcsbL5Qs8LyN8dWLlpjMRJFm+BmXNLrkTG3c3rC7WGzcOR7un3EjBm5qdwfFB8zsNJp1Z3CaG2unnHcrNcwNNC9NDqI231eP+Toq4AoaiLcNILm4i0i5TswwMQIDAQAB";
    //支付宝公钥
    public static final String NOTIFY_URL = "http://121.196.205.160:8080/pay/notify/";
    //你的支付回调地址
    public static final String RETURN_URL = "https://openapi.alipay.com/gateway.do";
    //你的支付回调地址
    public static final String SIGN_TYPE = "RSA2";
    //签名加密类型(固定)
    public static final String CHARSET = "utf-8";
    public static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    //支付宝网关(固定)
    public static final String FORMAT = "json";
}