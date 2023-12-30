from flask import Flask, render_template, Response, request, json, jsonify
from flask_cors import *
# from flask_restful import Resource, Api
import requests

from api.PhotoPage import taxons
from flask import send_from_directory
import os

from service import PhotoPageService, UserService, BloodPressureService, BodyIndexService, GetUserDate, UserDataService

# app = Flask(__name__)
app = Flask(__name__,
            template_folder="../dist",
            static_folder="../dist/static")

# app = Flask(__name__,
#            template_folder="../without_package/dist",
#            static_folder="../without_package/dist/static")


# api = Api(app)

CORS(app, supports_credentials=True)

app.register_blueprint(taxons, url_prefix="/taxons")


@app.route('/favicon.ico')  # 设置icon
def favicon():
    return send_from_directory(app.root_path,  # 对于当前文件所在路径,比如这里是static下的favicon.ico
                               'brain.png', mimetype='image/vnd.microsoft.icon')


# 登录
@app.route('/login', methods=['POST'])
def login():
    params = request.get_json()
    user_info = UserService.getUserInfo(params['username'], params['password'])

    result = {
        'code': 500,
        'message': '用户或密码错误！'
    }
    if user_info:
        result['code'] = 200
        result['message'] = '登录成功！'

    return result

# 获取列表
@app.route('/photoList', methods=['GET'])
def photoList():
    page = request.args.get("page")
    limit = request.args.get("limit")
    photo_list = PhotoPageService.getPhotoInfo(int(page), int(limit))
    result = {
        'code': 200,
        'data': {
            'list': photo_list[0],
            'total': photo_list[1]
        }
    }
    return result

@app.route('/bloodpressure', methods=['GET'])
def pressureList():
    page = request.args.get("page")
    limit = request.args.get("limit")
    photo_list = BloodPressureService.getPressureInfo(int(page), int(limit))
    result = {
        'code': 200,
        'data': {
            'list': photo_list[0],
            'total': photo_list[1],
            'index_name': photo_list[2]
        }
    }
    return result


@app.route('/userinfo', methods=['GET'])
def userinfoList():
    page = request.args.get("page")
    limit = request.args.get("limit")
    photo_list = UserDataService.getUserInfo(int(page), int(limit))
    result = {
        'code': 200,
        'data': {
            'list': photo_list[0],
            'total': photo_list[1]
        }
    }
    return result

@app.route('/bodyindex', methods=['GET'])
def bodyindexList():
    select_body_index = request.args.get("select_body_index")
    page = request.args.get("page")
    limit = request.args.get("limit")
    photo_list = BodyIndexService.getBodyindexInfo(select_body_index, int(page), int(limit))

    result = {
        'code': 200,
        'data': {
            'list': photo_list[0],
            'total': photo_list[1],
            'index_name': photo_list[2]
        }
    }
    return result

@app.route('/getdaytimedata', methods=['GET'])
def Getdaytimedatalist():

    photo_list = GetUserDate.GetUserDateList()

    result = {
        'code': 200,
        'data': {
            'x_axis': photo_list[0],
            'y_axis': photo_list[1]
        }
    }
    return result

@app.route('/gethourtimedata', methods=['GET'])
def Gethourtimedatalist():

    photo_list = GetUserDate.GetUserHourList()

    result = {
        'code': 200,
        'data': {
            'x_axis': photo_list[0],
            'y_axis': photo_list[1]
        }
    }
    return result


@app.route('/getdiettypedata', methods=['GET'])
def Getdiettypedatalist():

    photo_list = GetUserDate.GetDietTypeList()

    result = {
        'code': 200,
        'data': {
            'x_axis': photo_list
        }
    }
    return result



@app.route('/getaddbodyindex', methods=['GET'])
def Getaddbodyindexlist():

    photo_list = UserDataService.getBodyIndexInfo(0,10)

    result = {
        'code': 200,
        'data': {
             'list': photo_list[0],
             'total': photo_list[1],
        }
    }
    return result


@app.route('/editphoto', methods=['GET', 'POST'])
def GetEditPhoto():
    if request.method == 'POST':
        data1 = request.get_json()
        print(data1)
        PhotoPageService.editPhotoDatabase(data1['user_time'],data1['phone_id'],
                                           data1['photo_cal'],
                                            data1['photo_kind'],
                                           data1['photo_name'], data1['photo_path'],
                                           data1['photo_type'],
                                           data1['upload_time'], data1['user_group'],
                                           data1['user_name'])

    result = {
        'code': 200,
        'data': {

        }
    }
    return result


@app.route('/editeatflag', methods=['GET', 'POST'])
def editEafflagDatabase():
    if request.method == 'POST':
        data1 = request.get_json()
        print(data1)
        PhotoPageService.editEafflagDatabase(data1['user_name'],data1['phone_id'],
                                           data1['start_time'], data1['end_time'],
                                           data1['edit_flag'])

    result = {
        'code': 200,
        'data': {

        }
    }
    return result


@app.route('/editbodyindextype', methods=['GET', 'POST'])
def editBodyIndexTypeDatabase():
    if request.method == 'POST':
        data1 = request.get_json()
        print(data1)
        if data1['min_num'] == None:
            data1['min_num'] = 0
        if data1['max_num'] == None:
            data1['max_num'] = 0
        if data1['scale'] == None:
            data1['scale'] = 0
        if data1['average'] == None:
            data1['average'] = 0
        PhotoPageService.editBodyIndexTypeDatabase(data1['index_console'],data1['index_query'],
                                           data1['index_unit'], data1['min_num'],
                                           data1['max_num'],data1['scale'],data1['average'],
                                           data1['index_name'], data1['is_circadian'])

    result = {
        'code': 200,
        'data': {

        }
    }
    return result


# ------------------------------------------------------------------------------------------

@app.route('/', defaults={'path': ''})
@app.route('/<path:path>')
def catch_all(path):
    # print(path, 'catchall')
    # #if app.debug:
    # return requests.get('http://localhost:8089/{}'.format(path)).text
    # # return ""
    # #return render_template("index.html")
    #
    #
    # return requests.get('http://localhost:5000/{}'.format(path)).text
    if app.debug:
        return requests.get('http://localhost:81/{}'.format(path)).text
    return render_template("index.html")

def create_app():
    return app
if __name__ == '__main__':
    app.run(host="0.0.0.0", port=3001)
