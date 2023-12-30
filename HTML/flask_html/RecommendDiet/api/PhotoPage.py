from flask import Blueprint, request, json
from flask_cors import *

from service.PhotoPageService import getPhotoInfo
taxons = Blueprint('taxons', __name__)
CORS(taxons, supports_credentials=True)


@taxons.route('/photopage', methods=['GET'])
def PhotoInfo():
    # user_name = request.args.get("user_name")
    # user_group = request.args.get("user_group")
    # phone_id = request.args.get("phone_id")
    # upload_time = request.args.get("upload_time")
    # data_time = request.args.get("data_time")
    # photo_name = request.args.get("photo_name")
    # photo_path = request.args.get("photo_path")
    # photo_type = request.args.get("photo_type")
    # photo_cal = request.args.get("photo_cal")
    # photo_kind = request.args.get("photo_kind")
    # photo_type_anno = request.args.get("photo_type_anno")
    # photo_cal_anno = request.args.get("photo_cal_anno")
    page = request.args.get("page")
    limit = request.args.get("limit")

    # speciesRes = getSpecies(checkedSites, int(page), int(limit), propName, propOrder, secondPropName, secondPropOrder)
    celltypeRes = getPhotoInfo(int(page), int(limit))
    result = {
        # 'species': speciesRes,
        'photo': celltypeRes,
    }
    return result
