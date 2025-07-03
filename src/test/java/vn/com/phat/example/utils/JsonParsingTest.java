/*******************************************************************************
 * Class        ：JsonParsingTest
 * Created date ：2025/06/06
 * Lasted date  ：2025/06/06
 * Author       ：PhatLT
 * Change log   ：2025/06/06：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonParsingTest {

//    @Test
    public void testParseJsonFromString() throws org.json.JSONException {
        String jsonString = "{\"name\":\"Test Name\",\"value\":123}";
        JSONObject jsonObject = new JSONObject(jsonString);

        assertNotNull(jsonObject);
        assertEquals("Test Name", jsonObject.getString("name"));
        assertEquals(123, jsonObject.getInt("value"));
    }

//    @Test
    public void testParseEmptyJsonString() throws org.json.JSONException {
        String jsonString = "{}";
        JSONObject jsonObject = new JSONObject(jsonString);

        assertNotNull(jsonObject);
        assertEquals(0, jsonObject.length());
    }

//    @Test(expected = org.json.JSONException.class)
    public void testParseInvalidJsonString() throws org.json.JSONException {
        String jsonString = "invalid json";
        new JSONObject(jsonString);
    }


//    @Test
    public void testSerialize() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add(null);
        JSONArray jsonArray = new JSONArray(arrayList);
        System.out.println(jsonArray.toString());
        assertNotNull(jsonArray.toString());
        
    }


//    @Test
    public void testParseJsonArray(){
        // String json = "";
        String json = "[{\"stepDeployId\":1970,\"userId\":7685},{\"stepDeployId\":1969,\"userId\":5195}]";

        // loop through the array and print each object
        try {
            org.json.JSONArray jsonArray = new org.json.JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                assertNotNull(jsonObject);
                assertNotNull(jsonObject.get("stepDeployId"));
                assertNotNull(jsonObject.get("userId"));
                System.out.println("StepDeployId: " + jsonObject.get("stepDeployId") + ", User);Id: " + jsonObject.get("userId"));
                System.out.println(jsonObject.getClass().getSimpleName());

            }
            assertNotNull(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
