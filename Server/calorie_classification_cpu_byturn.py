# -*- coding: utf-8 -*-

from __future__ import print_function, division

import sys

import numpy as np
import matplotlib.pyplot as plt
import time
import os
import copy
import argparse
import torch
import torch.nn as nn
import torch.optim as optim
from torch.optim import lr_scheduler
import torchvision
from torchvision import datasets, models, transforms
from torch.utils.data import Dataset
from torch.utils.data import DataLoader
from torchvision.datasets import ImageFolder
from PIL import Image
from shutil import copyfile
import re


seed= 1
torch.manual_seed(seed)
# torch.cuda.manual_seed(seed)
# torch.cuda.manual_seed_all(seed)  # if you are using multi-GPU.
np.random.seed(seed)  # Numpy module.
torch.manual_seed(seed)
torch.backends.cudnn.benchmark = False
torch.backends.cudnn.deterministic = True
normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                     std=[0.229, 0.224, 0.225])

train_transformer_ImageNet = transforms.Compose([
    transforms.RandomResizedCrop(224),
    transforms.RandomHorizontalFlip(),
    transforms.ToTensor(),
    normalize
])

val_transformer_ImageNet = transforms.Compose([
    transforms.Resize(256),
    transforms.CenterCrop(224),
    transforms.ToTensor(),
    normalize
])

parser = argparse.ArgumentParser(description='PyTorch ImageNet Training')
# parser.add_argument('user', metavar='user', default='50470b6627e1e211')
# parser.add_argument('data', metavar='DIR',
#                     help='path to dataset')

args = parser.parse_args()

class MyDataset(Dataset):
    def __init__(self, filenames, labels, transform):
        self.filenames = filenames
        self.labels = labels
        self.transform = transform

    def __len__(self):  # 因为漏了这行代码，花了一个多小时解决问题
        return len(self.filenames)

    def __getitem__(self, idx):
        image = Image.open(self.filenames[idx]).convert('RGB')
        image = self.transform(image)
        return image, self.labels[idx]

def fetch_dataloaders(data_dir, ratio, batchsize=64):
    """ the sum of ratio must equal to 1"""
    dataset = ImageFolder(data_dir)
    character = [[] for i in range(len(dataset.classes))]
    for x, y in dataset.samples:  # 将数据按类标存放
        character[y].append(x)

    train_inputs, val_inputs, test_inputs = [], [], []
    train_labels, val_labels, test_labels = [], [], []
    for i, data in enumerate(character):
        num_sample_train = int(len(data) * ratio[0])
        num_sample_val = int(len(data) * ratio[1])
        num_val_index = num_sample_train + num_sample_val

        for x in data[:num_sample_train]:
            train_inputs.append(str(x))
            train_labels.append(i)
        for x in data[num_sample_train:num_val_index]:
            val_inputs.append(str(x))
            val_labels.append(i)
        for x in data[num_val_index:]:
            test_inputs.append(str(x))
            test_labels.append(i)

    train_dataloader = DataLoader(MyDataset(train_inputs, train_labels, train_transformer_ImageNet), batch_size=batchsize, shuffle=True, pin_memory = True, num_workers = 4, sampler=None)
    val_dataloader = DataLoader(MyDataset(val_inputs, val_labels, val_transformer_ImageNet), batch_size=batchsize, shuffle=False, pin_memory = True, num_workers = 4)
    test_dataloader = DataLoader(MyDataset(test_inputs, test_labels, val_transformer_ImageNet), batch_size=batchsize, pin_memory = True, num_workers = 4)
    loader = {}
    loader['train'] = train_dataloader
    loader['val'] = val_dataloader
    loader['test'] = test_dataloader

    return loader


# device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
device = "cpu"
#target_path = "/var/www/html/deeplearning_photo/temp_photo_calorie/train/0/" + args.data
# target_path = "/var/www/html/deeplearning_photo/temp_photo/train/0/" + args.data
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



data_dir = '/var/www/html/deeplearning_photo/temp_photo_byturn/'
train_folder = os.path.join(files, "train")
# val_folder = os.path.join(data_dir, "train")
# loader = fetch_dataloaders(train_folder, [0.6, 0.3, 0.1], batchsize=20)

val_dataloaders = torch.utils.data.DataLoader(
        datasets.ImageFolder(data_dir, transforms.Compose([
            transforms.Resize(256),
            transforms.CenterCrop(224),
            transforms.ToTensor(),
            normalize,
        ])),
        batch_size=1, shuffle=False,
        num_workers=4, pin_memory=True)


train_transforms = transforms.Compose([
    transforms.Resize([224, 224]),
    # transforms.RandomResizedCrop(224),
    transforms.RandomHorizontalFlip(),
    transforms.RandomVerticalFlip(),
    transforms.ToTensor(),
    transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
])
val_transforms = transforms.Compose([
    transforms.Resize([224, 224]),
    # transforms.CenterCrop(224),
    transforms.ToTensor(),
    transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
])

train_dataset = datasets.ImageFolder(train_folder, train_transforms)
# val_dataset = datasets.ImageFolder(val_folder, val_transforms)

# val_dataloaders = torch.utils.data.DataLoader(train_dataset, batch_size=1, shuffle=False, num_workers=4, pin_memory=True)
# val_dataloaders = torch.utils.data.DataLoader(val_dataset, batch_size=32, shuffle=False, num_workers=4)
# val_dataloaders = loader['val']
# train_dataset_sizes = len(train_dataset)
# val_dataset_sizes = len(val_dataset)
#class_names = train_dataset.classes
class_names = ['1', '10', '100', '103', '105', '107', '108', '109', '11', '110', '112', '113', '117', '12', '120', '121', '122', '125', '127', '129', '13', '133', '134', '135', '136', '137', '138', '139', '14', '141', '154', '156', '157', '16', '163', '164', '165', '166', '169', '17', '170', '171', '172', '173', '18', '180', '183', '184', '185', '186', '187', '189', '194', '195', '197', '2', '20', '201', '203', '207', '210', '211', '216', '217', '222', '224', '225', '228', '23', '235', '236', '244', '246', '252', '30', '31', '33', '34', '36', '42', '44', '45', '46', '47', '48', '52', '53', '55', '56', '59', '60', '61', '64', '65', '68', '7', '70', '71', '72', '73', '74', '75', '79', '80', '84', '86', '87', '88', '89', '90', '91', '92', '93', '94', '95', '98', '99']
# print(train_dataset.class_to_idx)
# print(class_names)
# print(train_dataset.imgs)


class AverageMeter(object):
    """Computes and stores the average and current value"""

    def __init__(self):
        self.reset()

    def reset(self):
        self.val = 0
        self.avg = 0
        self.sum = 0
        self.count = 0

    def update(self, val, n=1):
        self.val = val
        self.sum += val * n
        self.count += n
        self.avg = self.sum / self.count


def accuracy(output, labels, topk=(1,)):
    """Computes the accuracy over the k top predictions for the specified values of k"""
    with torch.no_grad():
        maxk = max(topk)
        batch_size = labels.size(0)

        _, pred = output.topk(maxk, 1, True, True)
        pred = pred.t()
        correct = pred.eq(labels.view(1, -1).expand_as(pred))

        res = []
        for k in topk:
            correct_k = correct[:k].view(-1).float().sum(0, keepdim=True)
            res.append(correct_k.mul_(100.0 / batch_size))
        return res


def accuracy_print(output, labels, topk=(1,)):
    """Computes the accuracy over the k top predictions for the specified values of k"""
    with torch.no_grad():
        maxk = max(topk)
        batch_size = labels.size(0)

        _, pred = output.topk(maxk, 1, True, True)
        pred = pred.t()
        correct = pred.eq(labels.view(1, -1).expand_as(pred))

        res = []
        for k in topk:
            correct_k = correct[:k].view(-1).float().sum(0, keepdim=True)
            res.append(correct_k.mul_(100.0 / batch_size))
        return res


def validate(val_loader, model, criterion):
    losses = AverageMeter()
    top1 = AverageMeter()
    top5 = AverageMeter()

    # switch to evaluate mode
    model.eval()

    with torch.no_grad():
        for i, (inputs, labels) in enumerate(val_loader):
            inputs = inputs.to(device)
            labels = labels.to(device)

            # compute output
            output = model(inputs)
            loss = criterion(output, labels)

            # measure accuracy and record loss
            acc1, acc5 = accuracy(output, labels, topk=(1, 5))
            losses.update(loss.item(), inputs.size(0))
            top1.update(acc1[0], inputs.size(0))
            top5.update(acc5[0], inputs.size(0))

            if i % 500 == 0:
                print('Test: [{0}/{1}], '
                      'Loss(avg): {loss.val:.4f}({loss.avg:.4f}), '
                      'Top1 acc(avg): {top1.val:.3f}({top1.avg:.3f}), '
                      'Top5 acc(avg): {top5.val:.3f}({top5.avg:.3f})'.format(
                    i, len(val_loader), loss=losses,
                    top1=top1, top5=top5))
        print(' * Top1 avg_acc {top1.avg:.3f} , Top5 avg_acc {top5.avg:.3f}'
              .format(top1=top1, top5=top5))

    return top1.avg


def train(train_loader, model, criterion, optimizer, epoch, num_epochs):
    losses = AverageMeter()
    top1 = AverageMeter()
    top5 = AverageMeter()

    # switch to train mode
    model.train()

    for i, (inputs, labels) in enumerate(train_loader):
        # measure data loading time

        inputs = inputs.to(device)
        labels = labels.to(device)

        # compute output
        output = model(inputs)
        loss = criterion(output, labels)

        # measure accuracy and record loss
        acc1, acc5 = accuracy(output, labels, topk=(1, 5))
        losses.update(loss.item(), inputs.size(0))
        top1.update(acc1[0], inputs.size(0))
        top5.update(acc5[0], inputs.size(0))

        # compute gradient and do SGD step
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        if i % 50 == 0:
            print('Epoch: [{0}/{1}][{2}/{3}], '
                  'Loss(avg): {loss.val:.4f}({loss.avg:.4f}), '
                  'Top1 acc(avg): {top1.val:.3f}({top1.avg:.3f}), '
                  'Top5 acc(avg): {top5.val:.3f}({top5.avg:.3f})'.format(
                epoch, num_epochs, i, len(train_loader),
                loss=losses, top1=top1, top5=top5))




def predict():
    model = models.resnet152(pretrained=True)
    # num_ftrs = model.fc.in_features
    # model.fc = nn.Linear(num_ftrs, len(class_names))
    #
    model = model.to(device)

    # model = nn.DataParallel(model)
    #model.load_state_dict(torch.load('C:/Users/wanglab/PycharmProjects/pytorch/model/detection/date_1110/model_best.pth.tar')['state_dict'])
    model.load_state_dict(torch.load("/var/www/html/deeplearning_photo/model/model_best0709.pth.tar", map_location='cpu')['state_dict'])
    model.eval()

    # low_cal = ['31', '34', '36', '53', '64', '70', '72', '86', '87', '88', '89', '90', '91', '93', '100', '125', '133',
    #            '135', '136', '137', '154', '157', '180', '184', '187', '246', '252']
    # mid_cal = ['1', '7', '11', '20', '23', '44', '46', '47', '48', '52', '59', '61', '68', '73', '80', '84', '92', '94',
    #            '99', '105', '107', '108', '109', '110', '113', '121', '122', '127', '129', '156', '166', '167', '170',
    #            '173', '183', '185', '186', '201', '203', '207', '210', '216', '235', '236', '244']
    # high_cal = ['2', '10', '12', '14', '16', '17', '18', '30', '42', '45', '55', '56', '60', '65', '71', '74', '79',
    #             '95', '98', '103', '112', '117', '120', '134', '138', '139', '141', '163', '164', '165', '169', '171',
    #             '172', '189', '194', '195', '197', '211', '217', '222', '224', '225', '228']

    low_cal = ['31', '33','34', '36', '53', '64', '70', '72', '75','86', '87', '88', '89', '90', '91', '93', '100', '125', '133',
               '135', '136', '137', '154', '157', '180', '184', '187', '246', '252']
    mid_cal = ['1', '7', '11', '20', '23', '43','44', '46', '47', '48', '52', '59', '61', '68', '73', '80', '84', '92', '94',
               '99', '105', '107', '108', '109', '110', '113', '121', '122', '127', '129', '156', '166',  '170',
               '173', '183', '185', '186', '201', '203', '207', '210', '216', '235', '236', '244']
    high_cal = ['2', '10', '12', '13','14', '16', '17', '18', '30', '42', '45', '55', '56', '60', '65', '71', '74', '79',
                '95', '98', '103', '112', '117', '120', '134', '138', '139', '141', '163', '164', '165', '169', '171',
                '172', '189', '194', '195', '197', '211', '217', '222', '224', '225', '228']



    top5 = AverageMeter()

    total_index = 0
    output_string = ""
    output_string_3 = ""
    output_string_php = ""

    with torch.no_grad():
        for i, (inputs, labels) in enumerate(val_dataloaders):
            inputs = inputs.to(device)
            labels = labels.to(device)
            outputs = model(inputs)


            _, preds = torch.max(outputs, 1)
            # print("batch %d" % i)
            for j in range(inputs.size()[0]):
                output_string_php += re.split(r'/', val_dataloaders.dataset.samples[i][0])[-1] + ":"
                # file_name = val_dataloaders.dataset.filenames[i * 20 + j]
                total_index += 1
                # print(
                #     "{} pred label:{}, true label:{}".format(len(preds), class_names[preds[j]], class_names[labels[j]]))

                # output_string = file_name + "," + class_names[preds[j]] + "," + class_names[labels[j]] + "\n"
                if class_names[preds[j]] in low_cal:
                    predict_lable = 1
                    output_string_php += "1"
                elif class_names[preds[j]] in mid_cal:
                    predict_lable = 2
                    output_string_php += "2"
                elif class_names[preds[j]] in high_cal:
                    predict_lable = 3
                    output_string_php += "3"
                if class_names[labels[j]] in low_cal:
                    true_lable = 1
                if class_names[labels[j]] in mid_cal:
                    true_lable = 2
                if class_names[labels[j]] in high_cal:
                    true_lable = 3
            output_string_php += "," + class_names[preds[j]] + ";"
    output_string_php = output_string_php[:-1]
    print(output_string_php)
                # output_string3 = file_name + "," + str(predict_lable) + "," + str(true_lable) + "\n"
                # file = open("E:/Deeplearning_project/food/result_multi_0709.txt",'a+')
                # file.write(output_string)
                # file.close()
                #
                # file = open("E:/Deeplearning_project/food/result_3_0709.txt", 'a+')
                # file.write(output_string3)
                # file.close()





if __name__ == "__main__":
    predict()
    # os.remove(target_path)