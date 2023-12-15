/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.OnClick;

/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(name = "数据库",anim = CoreAnim.none)
public class DatabaseFragment extends BaseFragment {


    /**
     * @return 返回为 null意为不需要导航栏
     */
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_database;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {




    }

    @Override
    protected void initListeners() {


    }
    @OnClick({R.id.query})
    public void onViewClicked(View view) {
//        EditText insert_text = (EditText)findViewById(R.id.insert_text);
//        String insert_data = insert_text.getText().toString();

//        EditText delete_text = (EditText)findViewById(R.id.delete_text);
//        String delete_data = delete_text.getText().toString();

//        EditText update_before_text = (EditText)findViewById(R.id.update_before_text);
//        String update_before_data = update_before_text.getText().toString();
//        EditText update_after_text = (EditText)findViewById(R.id.update_after_text);
//        String update_after_data = update_after_text.getText().toString();

//        TextView textView = (TextView)findViewById(R.id.query_text);

        //新建了一个test_db的数据库
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"test_db",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        switch (view.getId()){
//            case R.id.insert_button:
//                ContentValues values = new ContentValues();
//                values.put("name",insert_data);
//                db.insert("user",null,values);
//                break;
//            case R.id.clear_insert_button:
//                insert_text.setText("");
//                break;
//            case R.id.delete_button:
//                db.delete("user","name=?",new String[]{delete_data});
//                break;
//            case R.id.clear_delete_button:
//                delete_text.setText("");
//                break;
            //更新数据按钮
//            case R.id.update_button:
//                ContentValues values2 = new ContentValues();
//                values2.put("name", update_after_data);
//                db.update("user", values2, "name = ?", new String[]{update_before_data});
//                break;
//            //更新数据按钮后面的清除按钮
//            case R.id.clear_update_button:
//                update_before_text.setText("");
//                update_after_text.setText("");
//                break;

            //查询全部按钮
            case R.id.query:
                //创建游标对象
                Cursor cursor = db.query("user", new String[]{"name"}, null, null, null, null, null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                String textview_data = "";
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    textview_data = textview_data + "\n" + name;
                }
//                textView.setText(textview_data);
                break;
            //查询全部按钮下面的清除查询按钮
//            case R.id.clear_query:
//                textView.setText("");
//                textView.setHint("查询内容为空");
//                break;

            default:
                break;
        }
    }
}
