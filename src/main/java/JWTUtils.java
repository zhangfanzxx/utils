import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


public class JWTUtils {
    private JWTUtils() {
    }

    private static final String SECRET = "adffffff";
    private static final String EXP = "time";
    private static final String PAYLOAD = "payload";

    private static final Long TOKEN_TIME = 30 * 60 * 1000L;
    private static final Integer COOK_TIME = 30 * 60;
    private static ObjectMapper mapper =new ObjectMapper();
    private static <T> String sign(T obj, Long maxAge) {
        if(maxAge==null||maxAge<=0){
            maxAge=TOKEN_TIME;
        }
        try {
            JWTSigner signer =new JWTSigner(SECRET);
            Map<String,Object> claims=new HashMap<>();

            String json=mapper.writeValueAsString(obj);
            claims.put(PAYLOAD,json);
            claims.put(EXP,System.currentTimeMillis()+maxAge);
            return signer.sign(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> T unsign(String token,Class<T> clazz){
        JWTVerifier verifier=new JWTVerifier(SECRET);
        try {
            Map<String,Object> claims=verifier.verify(token);
            if(claims.containsKey(EXP)&&claims.containsKey(PAYLOAD)){
                long exp= (long) claims.get(EXP);
                long currentTime =System.currentTimeMillis();
                if(exp>currentTime){
                    String json= (String) claims.get(PAYLOAD);
                    return mapper.readValue(json, clazz);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
