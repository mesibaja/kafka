/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.image;

import org.apache.kafka.common.metadata.ProducerIdsRecord;


public final class ProducerIdsDelta {
    private long highestSeenProducerId;

    public ProducerIdsDelta(ProducerIdsImage image) {
        this.highestSeenProducerId = image.highestSeenProducerId();
    }

    public void setHighestSeenProducerId(long highestSeenProducerId) {
        this.highestSeenProducerId = highestSeenProducerId;
    }

    public long highestSeenProducerId() {
        return highestSeenProducerId;
    }

    public void finishSnapshot() {
        // Nothing to do
    }

    public void replay(ProducerIdsRecord record) {
        highestSeenProducerId = record.producerIdsEnd();
    }

    public ProducerIdsImage apply() {
        return new ProducerIdsImage(highestSeenProducerId);
    }
}
