package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.BlockedUserDTO;
import net.etfbl.indeks.model.BlockedAccount;
import net.etfbl.indeks.service.BlockedAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blocked-accounts")
public class BlockedAccountController {
    private final BlockedAccountService blockedAccountService;

    public BlockedAccountController(BlockedAccountService blockedAccountService) {
        this.blockedAccountService = blockedAccountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BlockedUserDTO>> getBlockedUsers(@PathVariable Long userId) {
        return ResponseEntity.ok(blockedAccountService.getBlockedUsers(userId));
    }

    @PostMapping("/{userId}/block/{blockedUserId}")
    public ResponseEntity<BlockedAccount> blockUser(@PathVariable Long userId, @PathVariable Long blockedUserId) {
        return ResponseEntity.ok(blockedAccountService.blockUser(userId, blockedUserId));
    }

    @DeleteMapping("/{userId}/unblock/{blockedUserId}")
    public ResponseEntity<Void> unblockUser(@PathVariable Long userId, @PathVariable Long blockedUserId) {
        blockedAccountService.unblockUser(userId, blockedUserId);
        return ResponseEntity.noContent().build();
    }
}
