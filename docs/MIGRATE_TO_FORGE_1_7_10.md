# Chest Cavity Beyond 迁移到 Forge 1.7.10 方案

> 当前仓库主线仍是 **Minecraft 1.21.1 + NeoForge + Java 21**。本文件用于跟踪到 **Forge 1.7.10** 的迁移进度。

## 迁移进度总览

- [x] 阶段 0：兼容性审计（脚本 + 报告）
- [x] 阶段 1：构建层迁移（建立 1.7.10 子工程骨架）
- [~] 阶段 2：核心系统迁移（已完成胸腔数据存储与基础同步框架）
- [~] 阶段 3：内容注册迁移（已建立 legacy 器官注册与示例器官）
- [~] 阶段 4：渲染与 UI 迁移（已提供 Container/GuiContainer 基础实现）
- [~] 阶段 5：玩法回归测试（已打通基础手术提取/死亡掉落链路）

## 已落地内容

### 1) 审计与规划

- 自动审计脚本：`tools/audit_legacy_compat.py`
- 审计结果：`docs/legacy_1_7_10_audit.md`

### 2) Forge 1.7.10 子工程（已创建）

位置：`legacy-1.7.10/`

- `build.gradle`：ForgeGradle 1.2 + MC 1.7.10（Java 8）
- `ChestCavityBeyondLegacy`：`@Mod` 入口（preInit/init/postInit）
- `CommonProxy` / `ClientProxy`：旧版代理结构

### 3) 核心数据迁移（部分完成）

- 胸腔数据结构：`ChestCavityData`（3×9 槽位 + NBT 序列化）
- 实体挂载方式：`ChestCavityProperties implements IExtendedEntityProperties`
- 生命周期注册：`EntityLifecycleHandler`（EntityConstructing / Player clone）
- 初始器官注入：`LegacyChestCavityInitHandler`（生物首次进入世界注入默认器官）

### 4) 网络迁移（基础完成）

- 通道：`SimpleNetworkWrapper`
- 包示例：`SyncChestCavityPacket`（ByteBufUtils + NBT 同步）
- 实体同步触发：`ChestCavitySyncHandler`（登录/跟踪事件 + 周边广播）
- 服务端技能包：`UseSkillPacket`、`SyncSelectedSlotPacket`（旧版 `IMessage`）
- `selectedSlot` 回写同步：服务端校验并回发 `SyncChestCavityPacket`
- 客户端提示包：`ChestOpenerMessagePacket`、`UnopenableChestCavityMessagePacket`
- 客户端槽位同步包：`SyncSelectedSlotClientPacket`（维护本地 selectedSlot 状态）

### 5) 内容注册迁移（推进中）

- `ModItems` 使用 `GameRegistry.registerItem`
- `ItemChestOpenerLegacy` 支持打开自身/目标生物胸腔界面
- 新增示例器官 `organ_basic_heart`、`organ_basic_lung`
- 新增 `LegacyOrganRegistry` 与 `ILegacyOrgan` 运行时接口

### 6) UI 迁移（基础完成）

- `LegacyGuiHandler`：GUI 路由（支持通过实体ID指定目标）
- `ContainerChestCavityLegacy`：3x9 胸腔槽位 + 玩家背包
- `GuiChestCavityLegacy`：`GuiContainer` 占位界面

### 7) 器官运行时（基础完成）

- `LegacyOrganTickHandler`：按生物 tick 执行器官行为
- `SlotOrganLegacy`：胸腔槽位仅允许已注册器官物品
- `BasicHeartOrgan`：示例心脏器官（周期性治疗）
- `BasicLungOrgan`：示例肺器官（周期性提供水下呼吸）
- `LegacySkillUtil`：器官主动技能触发入口（配合服务端技能包，支持 selectedSlot 兜底触发）
- `LegacyClientState`：客户端选中槽位状态缓存

### 8) 手术与掉落链路（基础完成）

- `ItemChestOpenerLegacy`：对生物潜行右键可执行基础器官提取（含耐久消耗）
- `LegacySurgeryHelper`：实现成功率判定、条件校验、玩家/目标双冷却、附魔加成、失败惩罚、器官损坏概率与稀有器官逻辑
- `LegacyOrganDropHandler`：生物死亡时掉落胸腔内器官
- `ModRecipes`：补齐开胸器基础合成配方

## 未完成清单（继续执行）

1. 器官系统：把现有大批器官行为（属性/技能触发）逐项回写到 legacy 运行时。
2. 技能与任务网络包：按旧版 `IMessage` 逐包迁移。
3. 手术细则：继续补齐更细的附魔映射与高级器官专属规则（基础损坏概率/稀有器官逻辑已落地）。
4. 客户端渲染任务：替换现代渲染入口与特效。
5. 联机压力测试：多人同步一致性与边界行为。

## 目录说明

本次迁移采用“并行目录”策略：

- `src/`：保留现有 NeoForge 1.21.1 实现（不破坏主线）。
- `legacy-1.7.10/`：新增 Forge 1.7.10 迁移实现。

在 legacy 功能达到可玩后，再决定是否切分分支或替换主线结构。
