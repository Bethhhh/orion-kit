package com.orion.able;

import com.orion.utils.json.Jsons;

/**
 * Json接口
 *
 * @author Li
 * @version 1.0.0
 * @date 2019/8/15 20:34
 */
public interface Jsonable {

    String toJsonString();

    default String toJSON() {
        return Jsons.toJSON(this);
    }

}
