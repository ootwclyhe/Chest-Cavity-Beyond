package net.zhaiji.chestcavitybeyond.legacy.client.state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LegacyRenderTaskState {
    private static final Map<String, Integer> GUARDIAN_LASER_TASKS = new HashMap<String, Integer>();

    private LegacyRenderTaskState() {
    }

    public static void addGuardianLaserTask(int attackerId, int targetId, int duration) {
        GUARDIAN_LASER_TASKS.put(attackerId + ":" + targetId, duration);
    }

    public static Map<String, Integer> getGuardianLaserTasks() {
        return GUARDIAN_LASER_TASKS;
    }

    public static void tick() {
        Iterator<Map.Entry<String, Integer>> iterator = GUARDIAN_LASER_TASKS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            int left = entry.getValue() - 1;
            if (left <= 0) {
                iterator.remove();
            } else {
                entry.setValue(left);
            }
        }
    }
}
