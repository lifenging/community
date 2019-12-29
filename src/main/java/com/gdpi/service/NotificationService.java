package com.gdpi.service;

import com.gdpi.bean.Notification;
import com.gdpi.bean.NotificationExample;
import com.gdpi.bean.User;
import com.gdpi.dto.NotificationDTO;
import com.gdpi.enums.NotificationStatusEnum;
import com.gdpi.enums.NotificationTypeEnums;
import com.gdpi.exception.CustomizeErrorCode;
import com.gdpi.exception.CustomizeException;
import com.gdpi.mapper.NotificationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PageInfo list(Integer userId, Integer page) {
        PageHelper.startPage(page, 5,"gmt_create desc");
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(Long.valueOf(userId));
        List<Notification> notifications = this.notificationMapper.selectByExample(example);
        PageInfo notificationDTOPageInfo = new PageInfo(notifications, 5);

        ArrayList<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnums.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        notificationDTOPageInfo.setList(notificationDTOS);

        return notificationDTOPageInfo;
    }

    public Long unreadCount(Integer userId) {

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(Long.valueOf(userId))
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());

        return notificationMapper.countByExample(example);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);

        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }

        if (notification.getReceiver() != Long.valueOf(user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnums.nameOfType(notification.getType()));

        return notificationDTO;
    }
}
