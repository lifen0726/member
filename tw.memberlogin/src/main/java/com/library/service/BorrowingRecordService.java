//package com.library.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.library.model.BorrowingRecord;
//import com.library.repository.BorrowingRecordRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BorrowingRecordService {
//
//    @Autowired
//    private BorrowingRecordRepository borrowingRecordRepository;
//
//    public List<BorrowingRecord> getAllBorrowingRecords() {
//        return borrowingRecordRepository.findAll();
//    }
//
//    public Optional<BorrowingRecord> getBorrowingRecordByIds(int userId, int inventoryId, LocalDateTime borrowingTime) {
//        return borrowingRecordRepository.findByIds(userId, inventoryId, borrowingTime);
//    }
//
//    public BorrowingRecord createBorrowingRecord(BorrowingRecord borrowingRecord) {
//        return borrowingRecordRepository.save(borrowingRecord);
//    }
//
//    public BorrowingRecord updateBorrowingRecord(BorrowingRecord borrowingRecord) {
//        return borrowingRecordRepository.save(borrowingRecord);
//    }
//
//    public void deleteBorrowingRecordByIds(int userId, int inventoryId, LocalDateTime borrowingTime) {
//        borrowingRecordRepository.deleteByIds(userId, inventoryId, borrowingTime);
//    }
//}

