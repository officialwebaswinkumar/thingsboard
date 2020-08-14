/**
 * Copyright © 2016-2020 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.service.edge.rpc.constructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.AdminSettings;
import org.thingsboard.server.common.data.id.AdminSettingsId;
import org.thingsboard.server.dao.util.mapping.JacksonUtil;
import org.thingsboard.server.gen.edge.AdminSettingsUpdateMsg;

@Slf4j
@Component
public class AdminSettingsUpdateMsgConstructor {

    public AdminSettingsUpdateMsg constructAdminSettingsUpdateMsg(AdminSettings adminSettings) {
        AdminSettingsUpdateMsg.Builder builder = AdminSettingsUpdateMsg.newBuilder()
                .setKey(adminSettings.getKey())
                .setJsonValue(JacksonUtil.toString(adminSettings.getJsonValue()));
        AdminSettingsId adminSettingsId = adminSettings.getId();
        if (adminSettingsId != null) {
            builder.setIdMSB(adminSettingsId.getId().getMostSignificantBits());
            builder.setIdLSB(adminSettingsId.getId().getLeastSignificantBits());
        }
        return builder.build();
    }

}
