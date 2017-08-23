package com.example.phoenixtree.util;

import android.support.annotation.NonNull;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Position3D;
import com.example.phoenixtree.Model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ej on 8/23/2017.
 */

public class Fake {
    static public Keyframe propagateKeyframe() {
        Keyframe keyframe = new Keyframe();

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("mike");
        roles.add(role);
        keyframe.setRoles(roles);

        Map<Role, String> mapLines = new HashMap<>();
        mapLines.put(role, "Hello world!");
        keyframe.setMapLines(mapLines);

        Map<Role, Position3D> mapPositons = new HashMap<>();
        Position3D position3D = new Position3D(1.0f, 2.0f, 3.0f);
        mapPositons.put(role, position3D);
        keyframe.setMapPositon(mapPositons);

        return keyframe;
    }
}
