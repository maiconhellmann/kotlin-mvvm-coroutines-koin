package com.maiconhellmann.architecture.data.remote.dto

import com.maiconhellmann.architecture.data.model.Rate

class RatesDto(success: Boolean,
               timestamp: Long,
               val rates: Rate) : Dto(success, timestamp)