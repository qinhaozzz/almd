* git是分布式版本控制系统
* git管理的是文件的修改,并发文件
* HEAD -> master -> 提交
#### git创建仓库
```git
git init
```
#### git日志查看
```git
git log
```
#### git版本提交
```git
# add是把工作区文件的<修改>提交到暂存区
1.git add readme.txt
# commit是把暂存区文件的<修改>提交到当前分支
2.git commit -m ""
```
#### git文件对比
```git
git diff HEAD -- readme.txt
```
#### git版本回退
> Git的版本回退速度非常快，因为Git在内部有个指向当前版本的HEAD指针，当你回退版本的时候，Git仅仅是把HEAD从指向另一个版本
```git
1.git reset --hard HEAD^
2.git reset --hard HEAD~2
# git 版本号为安全哈希算法生成的40位16进制码
3.git reset --hard 9a2cd
```
#### git查看历史命令
```git
git reflog
```
#### git撤销修改
```git
# 丢弃工作区文件的修改,使文件回到最近一次commit或add的状态
# 有--表示撤销修改,无--表示切换分支
1.git checkout -- readme.txt
# 丢弃暂存区文件的修改,使文件回答HEAD最新版本
2.git reset HEAD readme.txt
```