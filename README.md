# Magic
搭建一个神奇的框架，采用热门技术框架,实现代码复用，采用官方推荐架构，应用分为界面层+网域层（可选）+数据层，解耦分层，独立开发，各个模块单独集成学习相关技术;
所有框架在common依赖库中做好封装，在app应用中按需调用即可

一.界面层
1.定义：界面的作用是在屏幕上显示应用数据，并充当主要的用户互动点。每当数据发生变化时，无论是因为用户互动（例如按了某个按钮），还是因为外部输入（例如网络响应），界面都应随之更新，以反映这些变化
2.界面开发语言：Compose
3.应用向用户显示的信息称为界面状态，命名方式：功能 + UiState
4.状态容器：ViewModel
5.状态向下流动、事件向上流动称为单向数据流 (UDF)
6.在 LiveData 或 StateFlow 等可观察数据容器中公开界面状态
7.导航组件：Navigation
8.分页组件：Paging
9.Android动画：Android Motion



二.网域层
1.定义：网域层负责封装复杂的业务逻辑，或者由多个 ViewModel 重复使用的简单业务逻辑。
2.命名：用例以其负责的单一操作命名。具体命名惯例如下：一般现在时动词 + 名词/内容（可选）+ 用例。
3.使用：通过使用 operator 修饰符定义 invoke() 函数，将用例类实例作为函数进行调用。




三.数据层
1.定义：数据层包含应用数据和业务逻辑
2.命名：存储库类以其负责的数据命名：数据类型 + Repository；数据源类以其负责的数据以及使用的来源命名：数据类型 + 来源类型 + DataSource（Remote + Local）
3.本地数据库：Room
4.本地存储：DataStore
4.网络请求：Retrofit
5.线程处理：kotlin协程
6.依赖注入：Hilt
7.任务调度：WorkManager
8.网络缓存：Mutex
9.文件存储：File


四.辅助层
1.日志框架：MLog

五.UI框架（Material Design 3）
1.整体主题修改 theme.kt
2.整体尺寸修改  type.kt
3.整体形状尺寸修改 shape.kt
4.全局颜色文件 color.kt

五.预置组件
1.按钮

Button                      也是Filled button，填充按钮对比鲜明的表面颜色使其成为仅次于FAB的最突出的按钮，它用于流程中的最终或解除阻止操作。（Save，Confirm，Done）
ElevatedButton              提升按钮本质上是填充的按钮，具有较浅的背景颜色和阴影。为了防止阴影产生偏移，仅在必要时使用它们，例如当按钮需要与图案背景进行视觉分离时。（Reply，View all，Add to cart，Take out of trash）
FilledTonalButton           填充色调按钮的背景颜色较浅，标签颜色较深，使其在视觉上不如常规填充按钮突出。它们仍然用于流程中的最终或解除阻止操作，但这样做的重点较少。（Save，Confirm，Done）
OutlinedButton              对于需要注意但不是主要操作的操作，请使用外边框的按钮，例如查看全部或添加到购物车。这也是用来让某人有机会改变主意或退出流程的按钮。（Reply，View all，Add to cart，Take out of trash）
TextButton                  文本按钮的视觉突出度较低，因此应该用于低强调的操作，例如替代选项。（Learn more，View all，Change account，Turn on）

IconButton
IconToggleButton
FilledIconButton
FilledTonalIconButton
FilledIconToggleButton
FilledTonalIconToggleButton
OutlinedIconButton
OutlinedIconToggleButton

FloatingActionButton
SmallFloatingActionButton
LargeFloatingActionButton
ExtendedFloatingActionButton
ExtendedFloatingActionButton

2.顶部栏和底部栏
SmallTopAppBar
CenterAlignedTopAppBar
MediumTopAppBar
LargeTopAppBar
BottomAppBar
NavigationBar

3.卡片
Card
ElevatedCard
OutlinedCard

4.碎片
AssistChip
ElevatedAssistChip
FilterChip
ElevatedFilterChip
InputChip
SuggestionChip
ElevatedSuggestionChip

5.对话框
AlertDialog
DialogFragment

6.菜单
DropdownMenu

7.导航栏
NavigationBar

8.抽屉
ModalNavigationDrawer

9.宽屏侧边栏
NavigationRail

10.开关
Switch

11.输入框
TextField
OutlinedTextField

12.
Tab
LeadingIconTab

13.
Badge

14.
Checkbox

15.
Divider

16
ExposedDropdownMenuBox

17.
Snackbar
SnackbarHost

18.s
RadioButton

19.f
LinearProgressIndicator
CircularProgressIndicator

20.Scaffold

21.SwipeRefresh