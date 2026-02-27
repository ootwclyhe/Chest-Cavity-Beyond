# Chest Cavity Beyond (Forge 1.7.10 Legacy Port)

这个目录是 1.7.10 迁移子工程（与主线 1.21.1 并存）。

## 当前完成

- Forge 1.7.10 构建脚本模板（`build.gradle`）
- 基础 Mod 入口与 Proxy
- 胸腔数据从 Attachment 迁移为 `IExtendedEntityProperties`
- 基础网络通道从新版 payload 迁移到 `SimpleNetworkWrapper`
- 示例同步包 `SyncChestCavityPacket` + 同步触发器 `ChestCavitySyncHandler`
- 胸腔 UI 骨架：`LegacyGuiHandler` + `ContainerChestCavityLegacy` + `GuiChestCavityLegacy`
- 示例物品注册与交互入口（`Chest Opener` 右键可打开自身/目标生物胸腔界面）
- 示例器官系统（`LegacyOrganRegistry` + `LegacyOrganTickHandler` + `organ_basic_heart` + `organ_basic_lung`）
- 基础手术提取与死亡器官掉落（`LegacySurgeryHelper` + `LegacyOrganDropHandler`）
- 开胸器基础合成配方（`ModRecipes`）

## 下一步

1. 将现有器官 Item/行为逐项回写到 1.7.10 API。
2. 将任务/技能网络包和服务端逻辑全部迁移。
3. 对接实体掉落、手术流程和特殊能力。
4. 完成单机与联机回归测试。
