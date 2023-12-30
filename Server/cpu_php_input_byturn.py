# coding=utf-8
from sklearn import svm
import argparse
import os
import random
import shutil
import time
import warnings
import ssl
import torch
import torch.nn as nn
import torch.nn.parallel
import torch.backends.cudnn as cudnn
import torch.distributed as dist
import torch.optim
import torch.multiprocessing as mp
import torch.utils.data
import torch.utils.data.distributed
import torchvision.transforms as transforms
import torchvision.datasets as datasets
import torchvision.models as models
from torchvision import transforms
from torch.utils.data import DataLoader
from torch.utils.data import Dataset
from torchvision.datasets import ImageFolder
from PIL import Image
import numpy as np
import csv
import sys
from sklearn.externals import joblib
from shutil import copyfile
import re


seed = 1
torch.manual_seed(seed)
np.random.seed(seed)  # Numpy module.
random.seed(seed)  # Python random module.
torch.manual_seed(seed)
torch.backends.cudnn.benchmark = False
torch.backends.cudnn.deterministic = True
cudnn.deterministic = True


def _init_fn(worker_id):
    np.random.seed(int(1))



model_names = sorted(name for name in models.__dict__
                     if name.islower() and not name.startswith("__")
                     and callable(models.__dict__[name]))
parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
# parser.add_argument('data', metavar='DIR',
#                     help='path to dataset')
parser.add_argument('-a', '--arch', metavar='ARCH', default='resnet18',
                    choices=model_names,
                    help='model architecture: ' +
                        ' | '.join(model_names) +
                        ' (default: resnet18)')


parser.add_argument('-e', '--evaluate', dest='evaluate', action='store_true',
                    help='evaluate model on validation set')
parser.add_argument('--pretrained', dest='pretrained', action='store_true',
                    help='use pre-trained model')




parser.add_argument('--gpu', default=None, type=int,
                    help='GPU id to use.')


best_acc1 = 0

val_string = []
test_string = []

def main():
    args = parser.parse_args()





    main_worker(args.gpu, 0, args)











def main_worker(gpu, ngpus_per_node, args):
    global best_acc1
    args.gpu = gpu

    if args.gpu is not None:
        print("Use GPU: {} for training".format(args.gpu))


    print("=> using pre-trained model '{}'".format(args.arch))
    model = models.__dict__[args.arch](pretrained=True)

    extractor = nn.Sequential(*list(model.children())[:-1])
    # DistributedDataParallel will divide and allocate batch_size to all
    # available GPUs if device_ids are not set
    model.eval()



    feature_path = 'E:/Deeplearning_project/food/resnet_feature/'

    image_path = "E:/Deeplearning_project/food/meituan/0/4Jkouq5CSg4IAJGyy27BXqQYGwIfBCP_CYgDiMSfSX_4gmFcC6w6IHbb6AvMuCY_0scohmss9LtJWg-k-u7I4UHdS9p3h7-h2wfpsWVfxX8nY08TQIxe-DkxF3-YDtNHvJLBPMnbGaim65JmQfWVIQ.jpg"


    cudnn.benchmark = True

    # Data loading code
    # target_path = "/var/www/html/deeplearning_photo/temp_photo/train/0/" + args.data
    #target_path = "/var/www/html/deeplearning_photo/temp_photo/train/0/" + args.data
    files = "/var/www/html/deeplearning_photo/temp_photo_byturn/"
    if not os.path.exists(files):
        os.mkdir(files)
        files1 = files + 'train/'
        os.mkdir(files1)
        files2 = files1 + '0/'
        os.mkdir(files2)
    # target_path = files + 'train/0/' + args.data
    # args.data = "/var/www/html/deeplearning_photo/uploads/" + args.data
    #
    # copyfile(args.data, target_path)



    traindir = os.path.join(files, 'train')
    normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                     std=[0.229, 0.224, 0.225])
    train_loader = torch.utils.data.DataLoader(
        datasets.ImageFolder(traindir, transforms.Compose([
            transforms.Resize(256),
            transforms.CenterCrop(224),
            transforms.ToTensor(),
            normalize,
        ])),
        batch_size=1, shuffle=False,
        num_workers=4, pin_memory=True)


    output_list = []

    for i, (images, target) in enumerate(train_loader):

        target_cpu = target.cpu()
        num_target = target_cpu.numpy()
        list_target = num_target.tolist()[0]

        # compute output
        output = extractor(images)
        y = output.data.cpu().numpy().reshape(1, 2048)
        y = np.append(y, list_target)
        temp = y.tolist()
        output_list.append(temp)

    # csvfile = open('E:/Deeplearning_project/food/resnet_feature/train_1file/train_imagenet.csv', 'w', newline='')
    # writer = csv.writer(csvfile)
    # writer.writerows(output_list)
    # csvfile.close()



    data_set = np.array(output_list)
    test_data = data_set[:,0:-2] 
    temp_file_name = train_loader.dataset.samples
    file_name = []
    for i in range(temp_file_name.__len__()):
        file_name.append(re.split(r"/", temp_file_name[i][0])[-1])

    clf = joblib.load("/root/deeplearning/food/svm_food5k.m")
    #print(clf.predict(test_data))
    result = clf.predict(test_data)
    result_list = result.tolist()
    num = 0
    #print(result_list.__len__())
    output_string = ""
    for j in range(result_list.__len__()):
        if result_list[j] == "1.0":
            output_string += file_name[j] + ":" + "food;"

        else:
            output_string += file_name[j] + ":" + "not food;"
    output_string = output_string[:-1]
    print(output_string)



    #os.remove(target_path)









if __name__ == '__main__':
    main()
