package org.pdaodao.data.structure;

/**
 6。二叉树 高阶版 ->
 *          二叉查找树(BST): 任何节点,所有左子节点都小于当前节点，所有右子节点都大于当前节点
 *              插入操作
 *                  从根结点开始比较，大于就从根结点右子树继续比较，小于就从根结点左子树比较
 *                  直到根子树为空，插入对应位置
 *              删除操作
 *                  该A节点是叶子节点
 *                      找到父节点，把指向该节点的引用设置为null
 *                  该A节点有一个子节点
 *                      找到父节点，把指向该节点的引用设置为A节点的子节点
 *                  该A节点有两个子节点
 *                      找到大于A节点的最小节点，替换调A节点
 *              查找最大最小值
 *                  查找最大值
 *                     查找根的右节点，直到找不到右节点，则当前节点为最大
 *                  查找最小值
 *                      查找根的左节点，直到找不到左节点，则当前节点为最小
 *          二叉平衡树(AVL): 具有二叉树的全部特性，并且要求每个节点的左子树和右子树高度差不超过1
 *                    (插入操作很耗费性能,都需要通过旋转保持平衡，所以推出红黑树)
 *              插入操作
 *                  LL 通过左旋转
 *                  LR 先左旋转 在右旋转
 *                  RL 先右旋转 在左旋转
 *                  RR 通过右旋转
 *          红黑树(自平衡二叉平衡树): 根节点必须为黑色、
 *                                每个叶子节点(null)都是为黑色、
 *                                每个红色节点的子节点都是黑色的、
 *                                任何节点到任意叶子节点的路径都包含相同数量的黑色节点、
 *                                如果一个节点存在黑子节点，那么该节点肯定有两个子节点
 *              黑色完美平衡:任何节点到叶子节点的路径包含相同数量的黑色节点
 *              插入操作
 *                  1.红黑树为空树
 *                      直接设置根节点，颜色为黑色。
 *                  2.插入已存在节点
 *                      找到存在的节点，替换value值
 *                  3.插入的节点的父节点是黑色的
 *                      直接插入,无需自平衡。
 *                  4.插入的节点的父节点是红色的
 *                      4.1 叔叔节点存在并且为红色
 *                           爸爸和叔叔修改为黑色、爷爷修改为红色,在以爷爷节点继续处理
 *                      4.2 叔叔节点不存在或者为黑色节点、
 *                           4.2.1 并且插入节点的父亲节点是在爷爷节点的左侧
 *                              4.2.1.1 并且插入节点在父亲节点的左侧
 *                                  父节点设置为黑色、爷爷节点设置为红色、右旋转父节点
 *                              4.2.1.1 并且插入节点在父亲节点的右侧
 *                                  左旋转父节点，父节点设置为黑色、爷爷节点设置为红色、右旋转父节点
 *                           4.2.2 并且插入节点的父亲节点是在爷爷节点的右侧
 *                              4.2.2.1 并且插入节点在父节点的右侧
 *                                  父节点设置为黑色 、爷爷节点设置为红色、左旋转父节点
 *                              4.2.2.2 并且插入节点在父节点的左侧
 *                                  右旋转父节点，父节点设置为黑色、爷爷节点设置为红色，左旋转父节点
 *              2-3树
 *                  https://juejin.cn/post/6861766887450574856
 *              B树  属于多路平衡搜索树，对于M阶B树的特性
 *                  1. 树中每个节点至多有M个子节点，即等于至多含有M-1个关键字
 *                  2. 根结点不是叶子节点，至少有两个子节点
 *                  3. 除根节点外非叶子节点至少有m/2个子节点，即等于至少含有m/2-1个关键字
 *                  4. 所有叶子节点都在同一层级
 *                  5. 非叶节点的结构
 *                          n P0 K1 P1 K2 P2 ..... Kn Pn 数据结构
 *                          K1 K2 .... Kn 为节点关键字 k1 < k2 < k3 < .... < kn
 *                          P0 P1 .... Pn 为子节点的指针， P0指向的子节点的关键字都小于K1 ， P1指向的子节点的关键字都大于K1
 *                  m=4的4阶B树
 *                                 1 指针       37    指针
 *                            1 指针 25 指针       2 指针 40 指针 85 指针
 *                              1^18^   2^30^35^    ^38^   3^46^58^76^         1^95^
 *                   插入操作
 *                      1。先查找节点，在插入节点，如果发现不符合B树特性进行分裂操作。
 *                      2。插入后节点的中间位([m/2])关键字并入父节点。
 *                      3。左侧节点插入父节点的新插入关键字的左侧，右侧节点插入父节点的新插入关键字的右侧。
 *                      4。如果父节点关键字超出范围，继续向上分裂。
 *              b+树 属于多路平衡搜索树，对于M阶B+树的特性
 *                  1。树中每个节点至多有M个子节点，即等于至多含有M-1个关键字且最少有ceil(m / 2) - 1个关键字。
 *                  2。树中每个节点至多有M个子节点，即等于至多含有M个关键字
 *                  3. 除根节点外非叶子节点至少有m/2个子节点，即等于至少含有m/2个关键字(向上取整 )
 *                  4. 根结点不是叶子节点，至少有两个子节点
 *                  5. 所有叶节点都包含了整颗树全部关键字和关键字指向记录的指针，叶节点内关键字是顺序排列，叶节点之间也是有序排列，指针相连
 *                  6。所有非终端节点可以看成是索引，仅仅包含其子树最大或最小关键字。
 *                  b+树和b树的区别
 *                      1. b树的节点既存储数据信息，也会存储索引信息，b+树叶子节点存储数据信息和节点信息，非叶子节点只存储索引信息
 *                  b+树的优点
 *                      2. 节点可以包含更多的索引信息，有效的降低层高，减少磁盘的IO操作
 *                      1. b+加载到内存的无效数据少

 *
 *
 */
public interface P {
}