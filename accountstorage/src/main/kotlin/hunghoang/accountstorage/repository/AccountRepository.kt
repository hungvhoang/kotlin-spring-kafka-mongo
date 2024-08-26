package hunghoang.accountstorage.repository

import hunghoang.accountstorage.model.Account
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository : MongoRepository<Account, String>{
}