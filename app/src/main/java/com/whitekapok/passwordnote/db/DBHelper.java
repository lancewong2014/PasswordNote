package com.whitekapok.passwordnote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.whitekapok.passwordnote.MainApp;
import com.whitekapok.passwordnote.entity.DaoMaster;
import com.whitekapok.passwordnote.entity.DaoSession;
import com.whitekapok.passwordnote.entity.GroupEntity;
import com.whitekapok.passwordnote.entity.GroupEntityDao;
import com.whitekapok.passwordnote.entity.KeyEntity;
import com.whitekapok.passwordnote.entity.KeyEntityDao;
import com.whitekapok.passwordnote.entity.NoteEntity;
import com.whitekapok.passwordnote.entity.NoteEntityDao;
import com.whitekapok.passwordnote.entity.UserEntity;
import com.whitekapok.passwordnote.entity.UserEntityDao;
import com.whitekapok.passwordnote.helper.UserInfoHelper;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *
 * Created by Lance on 2017/5/27.
 */

public class DBHelper {
    public static final String DB_DEFAULT_NAME="password_note.db";
    public static final String DB_DEFAULT_SUF=".db";
    private static final DBHelper ourInstance = new DBHelper();
    private Database curDatabase;
    private Database commonDatabase;
    public static DBHelper getInstance() {
        return ourInstance;
    }

    private KeyEntityDao keyEntityDao;

    private NoteEntityDao noteEntityDao;
    private GroupEntityDao groupDao;
    private UserEntityDao userDao;
    private DaoSession daoSession;
    private DaoSession commonDaoSession;
    private DBHelper() {
        initCommonDatabase();
        initLogin();
    }

    /**
     *
     */
    public void initLogin(){
        KeyEntity loginKey=getKeyBean(KeyEntity.DEFAULT_USER,KeyEntity.KEY_LOGIN);
        if(loginKey==null|| TextUtils.isEmpty(loginKey.getValue())){
            UserInfoHelper.getInstance().setUserEntity(null);
        }else{
            UserInfoHelper.getInstance().setUserEntity(new Gson().fromJson(loginKey.getValue(), UserEntity.class));
            openDB(UserInfoHelper.getInstance().getUserEntity().getUid(),UserInfoHelper.getInstance().getUserEntity().getPassword());
        }
    }


    private void initCommonDatabase(){
        if(commonDatabase==null){
            UpdateHelper helper=new UpdateHelper(MainApp.getInstance(),DB_DEFAULT_NAME);
            commonDatabase=helper.getWritableDb();
        }
        commonDaoSession=new DaoMaster(commonDatabase).newSession();
        keyEntityDao=commonDaoSession.getKeyEntityDao();
        userDao=commonDaoSession.getUserEntityDao();
    }

    private void closeDB(){
        try {
            if(curDatabase!=null){
                noteEntityDao=null;
                groupDao=null;
                daoSession.clear();
                curDatabase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录后需要打开一个数据库
     * @param uid
     */
    public void openDB(String uid,String password){
        closeDB();
        UpdateHelper helper=new UpdateHelper(MainApp.getInstance(),uid+DB_DEFAULT_SUF);
        curDatabase = helper.getEncryptedWritableDb(password);
        daoSession=new DaoMaster(curDatabase).newSession();
        noteEntityDao=daoSession.getNoteEntityDao();
        groupDao=daoSession.getGroupEntityDao();
    }

    /**
     * 更新某一个key
     * @param uid uid
     * @param key key
     * @param value value
     */
    public void updateKeyValue(String uid,String key,String value){
        KeyEntity keyBean=new KeyEntity();
        keyBean.setKey(key);
        keyBean.setValue(value);
        keyBean.setUid(uid);
        KeyEntity oldBean=getKeyBean(uid,key);
        if(oldBean!=null){
            oldBean.setValue(value);
            keyEntityDao.update(oldBean);
        }else{
            keyEntityDao.insert(keyBean);
        }
    }

    public void updateLoginUser(UserEntity userEntity){
        KeyEntity keyBean=new KeyEntity();
        keyBean.setKey(KeyEntity.KEY_LOGIN);
        keyBean.setValue(new Gson().toJson(userEntity));
        keyBean.setUid(KeyEntity.DEFAULT_USER);
        KeyEntity oldBean=getKeyBean(KeyEntity.DEFAULT_USER,KeyEntity.KEY_LOGIN);
        if(oldBean!=null){
            oldBean.setValue(new Gson().toJson(userEntity));
            keyEntityDao.update(oldBean);
        }else{
            keyEntityDao.insert(keyBean);
        }
    }

    /**
     * 根据用户uid和key值查找
     * @param uid uid
     * @param key key
     * @return KeyEntity
     */
    public KeyEntity getKeyBean(String uid,String key){
        try {
            QueryBuilder builder= keyEntityDao.queryBuilder();
            builder.where(KeyEntityDao.Properties.Uid.eq(uid),
                    KeyEntityDao.Properties.Key.eq(key));
            List<KeyEntity> result=builder.list();
            if(result!=null&&!result.isEmpty()){
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入一条数据
     * @param noteEntity 需要保存的item
     */
    public void addNoteEntity(NoteEntity noteEntity){
        noteEntityDao.insert(noteEntity);
    }

    /**
     *批量插入应该用不到
     * @param noteEntities 批量插入
     */
    public void addNoteList(NoteEntity ... noteEntities){
        if(noteEntities!=null){
            noteEntityDao.insertOrReplaceInTx(noteEntities);
        }
    }
    /**
     * 获取分组列表
     * @param groupName 组名
     * @return List<NoteEntity>
     */
    public List<NoteEntity> getNoteListGroup(String groupName){
        QueryBuilder builder= noteEntityDao.queryBuilder();
        builder.where(NoteEntityDao.Properties.ItemGroup.eq(groupName));
        return builder.list();
    }

    /**
     * 获取所有的item
     * @return List<NoteEntity>
     */
    public List<NoteEntity> getNoteListAll(){
        QueryBuilder builder= noteEntityDao.queryBuilder();
        return builder.list();
    }

    /**
     * 根据群名查询分组信息
     * @param groupName 组名
     * @return GroupEntity
     */
    public GroupEntity getGroup(String groupName){
        QueryBuilder builder=groupDao.queryBuilder();;
        builder.where(GroupEntityDao.Properties.GroupName.eq(groupName));
        List<GroupEntity> result=builder.list();
        if(result!=null&&!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }

    /**
     * 更新群组信息
     * @param groupEntity 需要更新的组信息
     */
    public void updateGroup(GroupEntity groupEntity){
        groupDao.update(groupEntity);
    }


    /**************************************查询用户信息 start**********************************************************/
    public UserEntity getUser(String username){
        QueryBuilder builder=userDao.queryBuilder();
        builder.where(UserEntityDao.Properties.Username.eq(username));
        List<UserEntity> result=builder.list();
        if(result!=null&&!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }

    public void saveUser(UserEntity userEntity){
        userDao.save(userEntity);
    }

    /**************************************查询用户信息 end**********************************************************/
    public static class UpdateHelper extends DaoMaster.OpenHelper{

        public UpdateHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onUpgrade(db, oldVersion, newVersion);
        }
    }
}
