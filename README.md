# PictureSelector的拓展类

> PictureSelector工程：https://github.com/LuckSiege/PictureSelector

## 使用
- 你的项目继承 PictureSelectionModelExt ，仿照 PictureSelectorManager 写管理类即可。

## 思路
- 通过反射解除PictureSelectionModel的包装，基于PictureSelectionModel做界面跳转、分发相关的策略，跳转动画的修改等等。