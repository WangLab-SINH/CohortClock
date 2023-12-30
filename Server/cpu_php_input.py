# coding=utf8
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
import torch.utils.model_zoo as model_zoo
import numpy as np
import csv
from requests.utils import urlparse
from sklearn.externals import joblib
from shutil import copyfile
import torch.utils.model_zoo as model_zoo
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
parser.add_argument('data', metavar='DIR',
                    help='path to dataset')
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
    print("break point main worker start")
    global best_acc1
    args.gpu = gpu

    if args.gpu is not None:
        print("Use GPU: {} for training".format(args.gpu))


    print("=> using pre-trained model '{}'".format(args.arch))
    print("model start")
    print(args.arch)
    torch_home = os.path.expanduser(os.getenv('TORCH_HOME', '~/.torch'))
    model_dir = os.getenv('TORCH_MODEL_ZOO', os.path.join(torch_home, 'models'))
    parts = urlparse('https://download.pytorch.org/models/resnet152-b121ed2d.pth')
    filename = os.path.basename(parts.path)
    cached_file = os.path.join(model_dir, filename)
    print(torch_home)
    print(cached_file)
    #model1 = ResNet(Bottleneck, [3, 8, 36, 3])
    #model = model1.load_state_dict(torch.load('/root/.cache/torch/checkpoints/resnet152-b121ed2d.pth'))
    #model = model.load_state_dict(model_zoo.load_url(model_urls['resnet152']),model_dir='/root/.cache/torch/checkpoints/')
    model = models.__dict__[args.arch](pretrained=True)
    #model = models.__dict__[args.arch](pretrained=True)
    #model = torch.load('/root/.cache/torch/checkpoints/resnet152-b121ed2d.pth')
    print("model end")
    extractor = nn.Sequential(*list(model.children())[:-1])
    # DistributedDataParallel will divide and allocate batch_size to all
    # available GPUs if device_ids are not set
    model.eval()



    feature_path = 'E:/Deeplearning_project/food/resnet_feature/'

    image_path = "E:/Deeplearning_project/food/meituan/0/4Jkouq5CSg4IAJGyy27BXqQYGwIfBCP_CYgDiMSfSX_4gmFcC6w6IHbb6AvMuCY_0scohmss9LtJWg-k-u7I4UHdS9p3h7-h2wfpsWVfxX8nY08TQIxe-DkxF3-YDtNHvJLBPMnbGaim65JmQfWVIQ.jpg"


    cudnn.benchmark = True

    # Data loading code
    target_path = "/var/www/html/deeplearning_photo/temp_photo/train/0/CkW87q3f200311.jpg"
    args.data = "/var/www/html/deeplearning_photo/uploads/CkW87q3f200311.jpg"

    copyfile(args.data, target_path)



    traindir = os.path.join("/var/www/html/deeplearning_photo/temp_photo/", 'train')
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
    print("break point1")
    data_set = np.array(output_list)
    test_data = data_set[:,0:-2] # 测试特征空间

    clf = joblib.load("/root/deeplearning/food/svm_food5k.m")
    #print(clf.predict(test_data))
    
    result = clf.predict(test_data)
    result_list = result.tolist()
    num = 0
    print("break point2")
    #print(result_list.__len__())
    for j in range(result_list.__len__()):
        if result_list[j] == "1.0":
            print("food")
        else:
            print("not food")
            num += 1

    os.remove(target_path)









if __name__ == '__main__':
    main()
