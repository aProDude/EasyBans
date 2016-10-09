package com._xxprodudexx_.easybans.api;

import java.util.UUID;

public interface BanManagementAPI {

    void ban(UUID uuid, String reason);

    void unban(UUID uuid);

//    BanInfo getBanInfo(UUID uuid);
}
